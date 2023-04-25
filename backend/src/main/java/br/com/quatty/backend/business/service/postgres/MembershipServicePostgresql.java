package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.api.dto.filter.MembershipFilterParams;
import br.com.quatty.backend.api.dto.mapper.MembershipMapper;
import br.com.quatty.backend.api.dto.request.MembershipRequest;
import br.com.quatty.backend.api.dto.response.MembershipResponse;
import br.com.quatty.backend.api.dto.table.MembershipTableResponse;
import br.com.quatty.backend.business.entity.Athlete;
import br.com.quatty.backend.business.entity.Community;
import br.com.quatty.backend.business.entity.Membership;
import br.com.quatty.backend.business.entity.enums.ApplicationState;
import br.com.quatty.backend.business.entity.pk.MembershipPK;
import br.com.quatty.backend.business.service.MembershipService;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.infra.repository.MembershipRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MembershipServicePostgresql implements MembershipService {

    private MembershipRepository membershipRepository;
    private MembershipMapper membershipMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public MembershipResponse applyOnCommunity(MembershipRequest membershipRequest) {
        Membership membership = membershipMapper.membershipRequestToEntity(membershipRequest);
        membership = membershipRepository.save(membership);
        return membershipMapper.entityToMembershipResponse(membership);
    }

    @Override
    public MembershipResponse updateMembershipInfo(MembershipRequest membershipRequest) {
        Membership membership = verifyIfExist(membershipRequest.getAthleteId(), membershipRequest.getCommunityId());
        membershipMapper.updateMembershipValues(membership, membershipRequest);
        membership = membershipRepository.save(membership);
        return membershipMapper.entityToMembershipResponse(membership);
    }

    @Override
    public MembershipTableResponse findMembershipByFilter(MembershipFilterParams membershipFilterParams) {
        MembershipTableResponse membershipTableResponse = new MembershipTableResponse();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Membership> criteriaQuery = criteriaBuilder.createQuery(Membership.class);
        Root<Membership> root = criteriaQuery.from(Membership.class);
        Join<Membership, Athlete> athleteJoin = root.join("athlete");
        Join<Membership, Community> communityJoin = root.join("community");
        List<Predicate> predicates = new ArrayList<>();

        if (membershipFilterParams.getAthleteId() != null){
            predicates.add(criteriaBuilder.equal(athleteJoin.get("id"), membershipFilterParams.getAthleteId()));
        }
        if (membershipFilterParams.getCommunityId() != null){
            predicates.add(criteriaBuilder.equal(communityJoin.get("id"), membershipFilterParams.getCommunityId()));
        }
        if (membershipFilterParams.getApplicationState() != null && !membershipFilterParams.getApplicationState().isEmpty()){
            predicates.add(criteriaBuilder.equal(root.get("applicationState"),
                    ApplicationState.getApplicationState(membershipFilterParams.getApplicationState())));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        if (membershipFilterParams.getSortField() != null && !membershipFilterParams.getSortField().isEmpty()){
            if (membershipFilterParams.getSortOrder()!= null && membershipFilterParams.getSortOrder().equalsIgnoreCase("DESC")){
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(membershipFilterParams.getSortField())));
            }else {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(membershipFilterParams.getSortField())));
            }
        }
        TypedQuery<Membership> query = entityManager.createQuery(criteriaQuery);

        if (membershipFilterParams.getPage() >= 0 && membershipFilterParams.getSize() > 0) {
            int startPosition = membershipFilterParams.getPage() * membershipFilterParams.getPage();
            query.setFirstResult(startPosition);
            query.setMaxResults(membershipFilterParams.getSize());
        }

        List<Membership> memberships = query.getResultList();
        List<MembershipResponse> responses = memberships.stream().map(membershipMapper::entityToMembershipResponse).toList();
        membershipTableResponse.setMembershipResponseList(responses);
        return membershipTableResponse;
    }


    private Membership verifyIfExist(Long athleteId, Long communityId){
        var membershipPK = MembershipPK.builder().athleteId(athleteId).communityId(communityId).build();
        return membershipRepository.findById(membershipPK)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Error locating {} in database from Athlete id: {}, Community id: {}",
                        Membership.class.getName(), athleteId, communityId)));
    }

}
