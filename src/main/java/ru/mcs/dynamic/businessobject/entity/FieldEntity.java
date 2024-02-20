package ru.mcs.dynamic.businessobject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "field_table")
public class FieldEntity extends BasicEntity {

    @Column(name = "fieldName")
    private String fieldName;

    @Column(name = "dataType")
    private String dataType;

    @Column(name = "fieldNum")
    private int fieldNum;

    @Column(name = "isIndexed")
    private boolean isIndexed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id", referencedColumnName = "id", nullable = false)
    private ObjectEntity objectEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FieldEntity that = (FieldEntity) o;
        return fieldNum == that.fieldNum && isIndexed == that.isIndexed && Objects.equals(fieldName, that.fieldName) && Objects.equals(dataType, that.dataType) && Objects.equals(objectEntity, that.objectEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fieldName, dataType, fieldNum, isIndexed, objectEntity);
    }
}
