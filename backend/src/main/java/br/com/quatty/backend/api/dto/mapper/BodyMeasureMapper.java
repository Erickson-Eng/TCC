package br.com.quatty.backend.api.dto.mapper;

import br.com.quatty.backend.api.dto.request.BodyMeasureRequest;
import br.com.quatty.backend.api.dto.response.BodyMeasureResponse;
import br.com.quatty.backend.business.entity.BodyMeasure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BodyMeasureMapper {

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "athlete.id", source = "athleteId")
    BodyMeasure createMeasureForAthlete(BodyMeasureRequest bodyMeasureRequest);


    BodyMeasureResponse createResponseFromEntity(BodyMeasure bodyMeasure);
}
