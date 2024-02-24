package ru.mcs.dynamic.businessobject.mapper;

import org.mapstruct.Mapper;
import ru.mcs.dynamic.businessobject.dto.request.ObjectEntityRequest;
import ru.mcs.dynamic.businessobject.entity.ObjectEntity;

@Mapper(componentModel = "SPRING", uses = BaseMapper.class)
public interface ObjectMapper {
    ObjectEntity objectDTOToObject(ObjectEntityRequest request);
}
