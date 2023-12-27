package ru.mcs.dynamic.businessobject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "value_table")
public class ValueEntity extends BasicEntity {
    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name="field_id", referencedColumnName = "id")
    private FieldEntity fieldEntity;
}
