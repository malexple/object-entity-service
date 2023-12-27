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
public class ObjectEntityRequest {
    private String name;
    private List<FieldEntityRequest> fields;
}
