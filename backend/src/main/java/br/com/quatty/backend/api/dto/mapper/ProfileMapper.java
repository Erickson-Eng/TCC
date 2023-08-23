package br.com.quatty.backend.api.dto.mapper;

import br.com.quatty.backend.api.dto.request.ProfileRequest;
import br.com.quatty.backend.api.dto.response.AthleteResponse;
import br.com.quatty.backend.api.dto.response.ManagerResponse;
import br.com.quatty.backend.business.entity.Athlete;
import br.com.quatty.backend.business.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "attendanceList", ignore = true)
    @Mapping(target = "membershipSet", ignore = true)
    @Mapping(target = "bodyMeasures", ignore = true)
    @Mapping(target = "locale", source = "localeRequest")
    @Mapping(target = "user.id", source = "userId")
    Athlete athleteRequestToEntity(ProfileRequest profileRequest);

    @Mapping(target = "localeResponse", source = "locale")
    @Mapping(target = "birthDate", dateFormat = "dd/MM/yyyy HH:mm:ss")
    @Mapping(target = "bodyMeasureList", source = "bodyMeasures")
    AthleteResponse entityToAthleteResponse(Athlete athlete);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "attendanceList", ignore = true)
    @Mapping(target = "membershipSet", ignore = true)
    @Mapping(target = "bodyMeasures", ignore = true)
    Athlete updateAthleteValues(@MappingTarget Athlete athlete, ProfileRequest athleteRequest);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "gyms", ignore = true)
    @Mapping(target = "locale", source = "localeRequest")
    @Mapping(target = "user.id", source = "userId")
    Manager createManagerFromProfileDTO(ProfileRequest profileRequest);

    @Mapping(target = "localeResponse", source =  "locale")
    @Mapping(target = "gymResponseList", source = "gyms")
    ManagerResponse createResponseFromEntity(Manager manager);

}
