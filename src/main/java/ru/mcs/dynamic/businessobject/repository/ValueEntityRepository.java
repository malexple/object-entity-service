package ru.mcs.dynamic.businessobject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mcs.dynamic.businessobject.entity.ValueEntity;

public interface ValueEntityRepository extends JpaRepository<ValueEntity, Integer> {
}
