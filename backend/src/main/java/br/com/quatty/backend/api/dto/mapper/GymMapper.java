package br.com.quatty.backend.api.dto.mapper;

import br.com.quatty.backend.api.dto.request.GymRequest;
import br.com.quatty.backend.api.dto.response.GymResponse;
import br.com.quatty.backend.business.entity.Gym;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GymMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "locale.id", source = "localeId")
    Gym gymRequestToEntity(GymRequest gymRequest);

    @Mapping(target = "localeResponse", source = "locale")
    GymResponse entityToGymResponse(Gym gym);
}