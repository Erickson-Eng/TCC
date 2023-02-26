package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.api.dto.filter.MembershipFilterParams;
import br.com.quatty.backend.api.dto.mapper.MembershipMapper;
import br.com.quatty.backend.api.dto.request.MembershipRequest;
import br.com.quatty.backend.api.dto.response.MembershipResponse;
import br.com.quatty.backend.api.dto.table.MembershipTableResponse;
import br.com.quatty.backend.business.entity.Membership;
import br.com.quatty.backend.business.entity.pk.MembershipPK;
import br.com.quatty.backend.business.service.MembershipService;
import br.com.quatty.backend.infra.repository.MembershipRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MembershipServicePostgresql implements MembershipService {

    private MembershipRepository membershipRepository;
    private MembershipMapper membershipMapper;

    @Override
    public MembershipResponse applyOnCommunity(MembershipRequest membershipRequest) {
        return null;
    }

    @Override
    public MembershipResponse updateMembershipInfo(MembershipRequest membershipRequest) {
        return null;
    }

    @Override
    public MembershipTableResponse findMembershipByFilter(MembershipFilterParams membershipFilterParams) {
        return null;
    }

    private Optional<Membership> verifyIfExist(Long athleteId, Long communityId){
        var membershipPK = MembershipPK.builder().athleteId(athleteId).communityId(communityId).build();
        return membershipRepository.findById(membershipPK);
    }

}
