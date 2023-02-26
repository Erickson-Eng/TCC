package br.com.quatty.backend.api.dto.mapper;

import br.com.quatty.backend.api.dto.request.AthleteRequest;
import br.com.quatty.backend.api.dto.response.AthleteResponse;
import br.com.quatty.backend.business.entity.Athlete;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AthleteMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "membershipSet", ignore = true)
    Athlete athleteRequestToEntity(AthleteRequest athleteRequest);


    @Mapping(target = "localeResponse", source = "locale")
    @Mapping(target = "birthDate", dateFormat = "dd/MM/yyyy HH:mm:ss")
    AthleteResponse entityToAthleteResponse(Athlete athlete);

    @Mapping(target = "id", ignore = true)
    Athlete updateAthleteValues(@MappingTarget Athlete athlete, AthleteRequest athleteRequest);
}
