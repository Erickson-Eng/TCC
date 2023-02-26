package br.com.quatty.backend.api.dto.mapper;

import br.com.quatty.backend.api.dto.request.CommunityRequest;
import br.com.quatty.backend.api.dto.response.CommunityResponse;
import br.com.quatty.backend.business.entity.Community;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommunityMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "membershipSet", ignore = true)
    @Mapping(target = "bookingSet", ignore = true)
    Community communityRequestToEntity(CommunityRequest communityRequest);

    CommunityResponse entityToCommunityResponse(Community community);

    @Mapping(target = "id", ignore = true)
    Community updateCommunityValues(@MappingTarget Community community, CommunityRequest communityRequest);
}
