package ru.mcs.dynamic.businessobject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mcs.dynamic.businessobject.entity.ObjectEntity;

public interface ObjectEntityRepository extends JpaRepository<ObjectEntity, Integer> {

    ObjectEntity findByName(String name);
}
