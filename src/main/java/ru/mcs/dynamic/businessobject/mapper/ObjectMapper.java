package ru.mcs.dynamic.businessobject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mcs.dynamic.businessobject.dto.request.ObjectEntityRequest;
import ru.mcs.dynamic.businessobject.entity.ObjectEntity;

@Mapper(componentModel = "SPRING", uses = BaseMapper.class)
public interface ObjectMapper {

    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "fields", source = "entity.fields")
    ObjectEntityRequest objectToObjectDTO(ObjectEntity entity);

    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "fields", source = "request.fields")
    ObjectEntity objectDTOToObject(ObjectEntityRequest request);
}
