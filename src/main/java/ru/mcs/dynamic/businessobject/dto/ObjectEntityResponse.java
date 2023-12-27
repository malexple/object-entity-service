package ru.mcs.dynamic.businessobject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectEntityResponse {
    private long id;
    private String name;
    private List<FieldEntityResponse> fields;
}
