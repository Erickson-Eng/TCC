package br.com.quatty.backend.api.dto.mapper;

import br.com.quatty.backend.api.dto.request.MembershipRequest;
import br.com.quatty.backend.api.dto.response.MembershipResponse;
import br.com.quatty.backend.business.entity.Membership;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MembershipMapper {


    @Mapping(target = "athlete.id", source = "athleteId")
    @Mapping(target = "community.id", source = "communityId")
    @Mapping(target = "membershipPK.athleteId", source = "athleteId")
    @Mapping(target = "membershipPK.communityId", source = "communityId")
    @Mapping(target = "applicationState", source = "applicationState")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    Membership membershipRequestToEntity(MembershipRequest membershipRequest);

    @Mapping(target = "athleteId", source = "athlete.id")
    @Mapping(target = "athleteName", source = "athlete.firstName")
    @Mapping(target = "communityId", source = "community.id")
    @Mapping(target = "communityName", source = "community.name")
    @Mapping(target = "applicationState", source = "applicationState")
    @Mapping(target = "createdDate", dateFormat = "dd/MM/yyyy HH:mm:ss")
    @Mapping(target = "modifiedDate", dateFormat = "dd/MM/yyyy HH:mm:ss")
    MembershipResponse entityToMembershipResponse(Membership membership);

    @Mapping(target = "athlete", ignore = true)
    @Mapping(target = "community", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    void updateMembershipValues(@MappingTarget Membership membership, MembershipRequest membershipRequest);
}
