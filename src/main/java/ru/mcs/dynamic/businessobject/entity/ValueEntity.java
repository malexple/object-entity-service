package ru.mcs.dynamic.businessobject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "value_table")
public class ValueEntity extends BasicEntity {
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id", referencedColumnName = "id", nullable = false)
    private ObjectEntity objectEntity;

    @Column(name = "column_1")
    private String column_1;
}
