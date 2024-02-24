package ru.mcs.dynamic.businessobject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mcs.dynamic.businessobject.dto.request.ObjectEntityRequest;
import ru.mcs.dynamic.businessobject.dto.response.FieldEntityResponse;
import ru.mcs.dynamic.businessobject.dto.response.ObjectEntityResponse;
import ru.mcs.dynamic.businessobject.entity.FieldEntity;
import ru.mcs.dynamic.businessobject.entity.ObjectEntity;
import ru.mcs.dynamic.businessobject.entity.ValueEntity;
import ru.mcs.dynamic.businessobject.mapper.ObjectMapper;
import ru.mcs.dynamic.businessobject.repository.FieldEntityRepository;
import ru.mcs.dynamic.businessobject.repository.ObjectEntityRepository;
import ru.mcs.dynamic.businessobject.repository.ValueEntityRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ObjectEntityService {

    private final ObjectEntityRepository objectEntityRepository;
    private final FieldEntityRepository fieldEntityRepository;
    private final ValueEntityRepository valueEntityRepository;
    private final ObjectMapper mapper;

    public ObjectEntityResponse createObjectEntity(ObjectEntityRequest objectEntityRequest) {
        ObjectEntity objectEntity = mapper.objectDTOToObject(objectEntityRequest);
        setFieldNum(objectEntity.getFields());

        objectEntityRepository.saveAndFlush(objectEntity);
        log.info("ObjectEntity {} is saved", objectEntity.getId());
        return mapper.objectToObjectDTO(objectEntity);
    }

    private void setFieldNum(Set<FieldEntity> fields) {
        int index = 1;
        for (FieldEntity field : fields) {
            field.setFieldNum(index++);
        }
    }

    public List<ObjectEntityResponse> getAllObjectEntity() {
        List<ObjectEntity> objectEntities = objectEntityRepository.findAll();

        return objectEntities.stream().map(this::mapToObjectEntityResponse).toList();
    }

    private ObjectEntityResponse mapToObjectEntityResponse(ObjectEntity objectEntity) {
        List<FieldEntityResponse> fieldEntityResponseList = objectEntity.getFields().stream()
                .map(this::mapToResponse).toList();

        return ObjectEntityResponse.builder()
                .id(objectEntity.getId())
                .name(objectEntity.getName())
                .fields(fieldEntityResponseList)
                .build();
    }

    private FieldEntityResponse mapToResponse(FieldEntity fieldEntity) {
        return FieldEntityResponse.builder()
                .fieldName(fieldEntity.getFieldName())
                .dataType(fieldEntity.getDataType()).build();
    }

    public void createDataEntity(Map<String,String> parameters) {
        for (String key : parameters.keySet()) {
            System.out.printf("%s : %s%n", key, parameters.get(key));
        }
        ObjectEntity objectEntity = objectEntityRepository.findByName(parameters.get("Object"));
        if (objectEntity != null) {
            Map<Integer, String> fielMap = fieldEntityRepository.getFieldMap(objectEntity);
            ValueEntity valueEntity = createValueEntity(objectEntity, fielMap);
            valueEntityRepository.saveAndFlush(valueEntity);
            log.info("ValueEntity {} is saved", objectEntity.getId());
        }
    }

    private ValueEntity createValueEntity(ObjectEntity objectEntity, Map<Integer, String> fielMap) {
        return ValueEntity.builder().
                objectEntity(objectEntity)
                .name(objectEntity.getName())
                .column_1(fielMap.get(2))
                .build();
    }
}
