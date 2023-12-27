package ru.mcs.dynamic.businessobject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "object_table")
public class ObjectEntity extends BasicEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "objectEntity", cascade = CascadeType.ALL)
    private List<FieldEntity> fields;
}
