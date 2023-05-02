package br.com.quatty.backend.api.dto.mapper;

import br.com.quatty.backend.api.dto.request.PracticableRequest;
import br.com.quatty.backend.api.dto.response.PracticableResponse;
import br.com.quatty.backend.business.entity.Practicable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PracticableMapper {


    @Mapping(target = "gym.id", source = "gymId")
    @Mapping(target = "sport.id", source = "sportId")
    @Mapping(target = "practicablePK.gymId", source = "gymId")
    @Mapping(target = "practicablePK.sportId", source = "sportId")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    Practicable practicableRequestToEntity(PracticableRequest practicableRequest);

    @Mapping(target = "gymId", source = "gym.id")
    @Mapping(target = "gymName", source = "gym.name")
    @Mapping(target = "sportResponse", source = "sport")
    PracticableResponse entityToPracticableResponse(Practicable practicable);
}
