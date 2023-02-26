package br.com.quatty.backend.api.dto.mapper;

import br.com.quatty.backend.api.dto.request.SportRequest;
import br.com.quatty.backend.api.dto.response.SportResponse;
import br.com.quatty.backend.business.entity.Sport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    Sport sportRequestToEntity(SportRequest sportRequest);

    SportResponse entityToSportResponse(Sport sport);

    @Mapping(target = "id", ignore = true)
    Sport updateSportValues(@MappingTarget Sport sport, SportRequest sportRequest);
}
