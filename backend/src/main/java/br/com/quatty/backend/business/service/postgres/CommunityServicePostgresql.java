package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.api.dto.mapper.CommunityMapper;
import br.com.quatty.backend.api.dto.request.CommunityRequest;
import br.com.quatty.backend.api.dto.request.MembershipRequest;
import br.com.quatty.backend.api.dto.response.CommunityResponse;
import br.com.quatty.backend.api.dto.table.CommunityTableResponse;
import br.com.quatty.backend.business.entity.Athlete;
import br.com.quatty.backend.business.entity.Community;
import br.com.quatty.backend.business.entity.enums.ApplicationState;
import br.com.quatty.backend.business.entity.enums.CommunityProfile;
import br.com.quatty.backend.business.service.AthleteService;
import br.com.quatty.backend.business.service.CommunityService;
import br.com.quatty.backend.business.service.MembershipService;
import br.com.quatty.backend.business.service.exception.DatabaseViolationException;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.infra.repository.CommunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommunityServicePostgresql implements CommunityService {

    private AthleteService athleteService;
    private MembershipService membershipService;
    private CommunityRepository communityRepository;
    private CommunityMapper communityMapper;

    @Override
    public CommunityResponse createCommunity(CommunityRequest communityRequest) {

        var entity = communityMapper.communityRequestToEntity(communityRequest);

        try{
            entity = communityRepository.save(entity);
            if (entity.getCreatedBy() != null) {
                Athlete athlete = athleteService.findAthleteByUsername(entity.getCreatedBy());
                MembershipRequest request = MembershipRequest.builder()
                        .communityId(entity.getId())
                        .athleteId(athlete.getId())
                        .applicationState(ApplicationState.APPROVED.getCode())
                        .communityProfile(CommunityProfile.ADMINISTRATOR.getCode())
                        .build();

                membershipService.applyOnCommunity(request);
            }
            return communityMapper.entityToCommunityResponse(entity);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseViolationException("Não foi possivel salvar os valores.");
        }
    }

    @Override
    public CommunityResponse updateCommunity(Long id, CommunityRequest communityRequest) {
        var entity = verifyIfExist(id);
        entity = communityMapper.updateCommunityValues(entity, communityRequest);
        entity = communityRepository.save(entity);
        return communityMapper.entityToCommunityResponse(entity);
    }

    @Override
    public CommunityTableResponse findCommunityByName(String name) {
        var entityList = verifyIfExist(name);
        var responseList = entityList.stream().map(communityMapper::entityToCommunityResponse).toList();
        return CommunityTableResponse.builder().communityResponseList(responseList).build();
    }

    @Override
    public void deleteCommunity(Long id) {
        var entity = verifyIfExist(id);
        communityRepository.delete(entity);
    }

    private List<Community> verifyIfExist(String name){
        return communityRepository.findAllByName(name);
    }

    private Community verifyIfExist(Long id){
        return communityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Error locating {} in database from id {}", Community.class.getName(), id)));
    }
}
