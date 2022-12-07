package com.cpe.sumulationserver.mapper;

import com.cpe.sumulationserver.dto.SimpleEntityDTO;
import com.cpe.sumulationserver.model.SimpleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SimpleSourceDestinationMapper {
    SimpleEntityDTO simpleEntityToSimpleEntityDTO(SimpleEntity simpleEntity);
}
