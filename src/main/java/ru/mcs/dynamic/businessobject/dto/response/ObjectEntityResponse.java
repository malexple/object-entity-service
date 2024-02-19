package ru.mcs.dynamic.businessobject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectEntityResponse {
    private UUID id;
    private String name;
    private List<FieldEntityResponse> fields;
}
