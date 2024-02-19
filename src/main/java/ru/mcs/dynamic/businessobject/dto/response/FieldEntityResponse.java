package ru.mcs.dynamic.businessobject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldEntityResponse {
    private String fieldName;
    private String dataType;
}
