package ru.mcs.dynamic.businessobject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mcs.dynamic.businessobject.entity.FieldEntity;
import ru.mcs.dynamic.businessobject.entity.ObjectEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface FieldEntityRepository extends JpaRepository<FieldEntity, Integer> {
    List<FieldEntity> findByObjectEntity(ObjectEntity objectEntity);
    default Map<Integer, String> getFieldMap(ObjectEntity objectEntity) {
        return findByObjectEntity(objectEntity).stream().collect(Collectors.toMap(FieldEntity::getFieldNum, FieldEntity::getFieldName));
    }
}
