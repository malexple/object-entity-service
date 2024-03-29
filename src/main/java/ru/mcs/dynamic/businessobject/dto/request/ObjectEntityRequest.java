package ru.mcs.dynamic.businessobject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectEntityRequest {
    private String name;
    private Set<FieldEntityRequest> fields;
}
