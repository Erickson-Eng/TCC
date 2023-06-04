package br.com.quatty.backend.business.service;

import br.com.quatty.backend.api.dto.filter.MembershipFilterParams;
import br.com.quatty.backend.api.dto.request.MembershipRequest;
import br.com.quatty.backend.api.dto.response.MembershipResponse;
import br.com.quatty.backend.api.dto.table.MembershipTableResponse;

public interface MembershipService {

    MembershipResponse applyOnCommunity(MembershipRequest membershipRequest);

    MembershipResponse updateMembershipInfo(MembershipRequest membershipRequest);

    MembershipTableResponse findMembershipByFilter(MembershipFilterParams membershipFilterParams);

    MembershipTableResponse findAllMembersForCommunity(Long communityId);
}
