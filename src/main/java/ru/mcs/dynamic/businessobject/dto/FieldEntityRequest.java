package ru.mcs.dynamic.businessobject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldEntityRequest {
    private String fieldName;
    private String dataType;
}
