package ru.mcs.dynamic.businessobject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "object_table")
public class ObjectEntity extends BasicEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "objectEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<FieldEntity> fields = new HashSet<>();

    public void setFields(Set<FieldEntity> fields) {
        for (FieldEntity field : fields) {
            setField(field);
        }
    }

    public void setField(FieldEntity field) {
        field.setObjectEntity(this);
        this.fields.add(field);
    }
}
