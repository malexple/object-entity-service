package ru.mcs.dynamic.businessobject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "field_table")
public class FieldEntity extends BasicEntity {

    @Column(name = "fieldName")
    private String fieldName;

    @Column(name = "dataType")
    private String dataType;

    @Column(name = "indexed")
    private boolean indexed;

    @Column(name = "fieldNum")
    private int fieldNum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "object_id",  referencedColumnName = "id")
    @BatchSize(size = 10)
    private ObjectEntity objectEntity;

    @OneToMany(mappedBy = "fieldEntity", cascade = CascadeType.ALL)
    @Lazy
    private List<ValueEntity> valueEntities;
}
