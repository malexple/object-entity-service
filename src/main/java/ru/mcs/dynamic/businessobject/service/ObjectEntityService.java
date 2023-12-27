package ru.mcs.dynamic.businessobject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mcs.dynamic.businessobject.dto.FieldEntityRequest;
import ru.mcs.dynamic.businessobject.dto.FieldEntityResponse;
import ru.mcs.dynamic.businessobject.dto.ObjectEntityRequest;
import ru.mcs.dynamic.businessobject.dto.ObjectEntityResponse;
import ru.mcs.dynamic.businessobject.entity.FieldEntity;
import ru.mcs.dynamic.businessobject.entity.ObjectEntity;
import ru.mcs.dynamic.businessobject.repository.FieldEntityRepository;
import ru.mcs.dynamic.businessobject.repository.ObjectEntityRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ObjectEntityService {

    private final ObjectEntityRepository objectEntityRepository;
    private final FieldEntityRepository fieldEntityRepository;

    public void createObjectEntity(ObjectEntityRequest objectEntityRequest) {
        List<FieldEntity> fieldEntityList = objectEntityRequest.getFields().stream()
                .map(this::mapToDto).toList();

        ObjectEntity objectEntity = ObjectEntity.builder()
                .name(objectEntityRequest.getName())
                .fields(fieldEntityList)
                .build();
        objectEntity.setCreatedAt(Instant.now());
        objectEntityRepository.saveAndFlush(objectEntity);
        log.info("ObjectEntity {} is saved", objectEntity.getId());

    }
    private FieldEntity mapToDto(FieldEntityRequest fieldEntityRequest) {
        FieldEntity fieldEntity = new FieldEntity();
        fieldEntity.setFieldName(fieldEntityRequest.getFieldName());
        fieldEntity.setDataType(fieldEntityRequest.getDataType());
        fieldEntity.setCreatedAt(Instant.now());
        return fieldEntity;
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
}
