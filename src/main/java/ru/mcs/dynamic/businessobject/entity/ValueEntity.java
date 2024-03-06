package ru.mcs.dynamic.businessobject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.lang.reflect.Field;

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

    @Column(name = "column_2")
    private String column_2;

    @Column(name = "column_3")
    private String column_3;

    @Column(name = "column_4")
    private String column_4;

    @Column(name = "column_5")
    private String column_5;

    @Column(name = "column_6")
    private String column_6;

    public void setFieldValue(Integer index, String value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(ValueEntity.class, String.format("column_%s", index));
        field.setAccessible(true);
        field.set(this, value);
    }

    private static Field getField(Class mClass, String fieldName) throws NoSuchFieldException {
        try {
            return mClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClass = mClass.getSuperclass();
            if (superClass == null) {
                throw e;
            } else {
                return getField(superClass, fieldName);
            }
        }
    }
}
