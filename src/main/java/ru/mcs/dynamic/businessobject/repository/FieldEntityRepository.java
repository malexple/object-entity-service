package ru.mcs.dynamic.businessobject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mcs.dynamic.businessobject.entity.FieldEntity;

public interface FieldEntityRepository extends JpaRepository<FieldEntity, Integer> {
}
