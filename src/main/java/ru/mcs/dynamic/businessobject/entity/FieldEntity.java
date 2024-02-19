package ru.mcs.dynamic.businessobject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
