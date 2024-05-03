package ru.mcs.dynamic.businessobject.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mcs.dynamic.businessobject.dto.request.DataEntityRequest;
import ru.mcs.dynamic.businessobject.dto.request.ObjectEntityRequest;
import ru.mcs.dynamic.businessobject.dto.response.FieldEntityResponse;
import ru.mcs.dynamic.businessobject.dto.response.ObjectEntityResponse;
import ru.mcs.dynamic.businessobject.entity.FieldEntity;
import ru.mcs.dynamic.businessobject.entity.ObjectEntity;
import ru.mcs.dynamic.businessobject.entity.ValueEntity;
import ru.mcs.dynamic.businessobject.error.ServiceException;
import ru.mcs.dynamic.businessobject.mapper.ObjectMapper;
import ru.mcs.dynamic.businessobject.repository.FieldEntityRepository;
import ru.mcs.dynamic.businessobject.repository.ObjectEntityRepository;
import ru.mcs.dynamic.businessobject.repository.ValueEntityRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static ru.mcs.dynamic.businessobject.error.DataErrors.CREATE_DATA_ERROR;
import static ru.mcs.dynamic.businessobject.error.ObjectError.CREATE_OBJECT_ERROR;

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

        try {
            objectEntityRepository.saveAndFlush(objectEntity);
            log.info("ObjectEntity {} is saved", objectEntity.getId());
        } catch (Exception e) {
            throw new ServiceException(CREATE_OBJECT_ERROR);
        }
        return mapper.objectToObjectDTO(objectEntity);
    }

    private void setFieldNum(Set<FieldEntity> fields) {
        int index = 0;
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

    public void createDataEntity(DataEntityRequest dataEntityRequest) {
        ObjectEntity objectEntity = objectEntityRepository.findByName(dataEntityRequest.getObjectName());
        if (objectEntity != null) {
            ValueEntity valueEntity = createValueEntity(objectEntity, dataEntityRequest.getValues());
            valueEntityRepository.saveAndFlush(valueEntity);
            log.info("ValueEntity {} is saved", objectEntity.getId());
        } else {
            throw new ServiceException(CREATE_DATA_ERROR);
        }
    }

    @SneakyThrows
    private ValueEntity createValueEntity(ObjectEntity objectEntity, Map<String, String> fields) {
        ValueEntity valueEntity = ValueEntity.builder().objectEntity(objectEntity).build();

        for (String key : fields.keySet()) {
            if (key.equals("Name")) {
                valueEntity.setName(fields.get(key));
            } else {
                int fieldNum = getFieldNum(key, objectEntity.getFields());
                valueEntity.setFieldValue(fieldNum, fields.get(key));
            }
        }

        return valueEntity;
    }

    private int getFieldNum(String fieldName, Set<FieldEntity> fields) {
        FieldEntity fieldEntity = fields.stream().filter(field -> field.getFieldName().equals(fieldName)).findFirst().get();
        return fieldEntity.getFieldNum();
    }
}
