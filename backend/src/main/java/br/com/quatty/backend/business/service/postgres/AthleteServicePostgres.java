package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.api.dto.filter.AthleteFilterParams;
import br.com.quatty.backend.api.dto.mapper.AthleteMapper;
import br.com.quatty.backend.api.dto.request.AthleteRequest;
import br.com.quatty.backend.api.dto.response.AthleteResponse;
import br.com.quatty.backend.api.dto.table.AthleteTableResponse;
import br.com.quatty.backend.business.entity.Athlete;
import br.com.quatty.backend.business.service.AthleteService;

import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.infra.repository.AthleteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AthleteServicePostgres implements AthleteService {

    private AthleteRepository athleteRepository;
    private AthleteMapper athleteMapper;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public AthleteResponse createAthlete(AthleteRequest athleteRequest) {
        try{
            var entity = athleteMapper.athleteRequestToEntity(athleteRequest);
            entity = athleteRepository.save(entity);
            return athleteMapper.entityToAthleteResponse(entity);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não foi possível salvar");
        }
    }

    @Override
    public AthleteResponse updateAthleteInfo(Long id, AthleteRequest athleteRequest) {
        try{
            var entity = verifyIfExist(id);
            entity = athleteMapper.updateAthleteValues(entity, athleteRequest);
            entity = athleteRepository.save(entity);
            return athleteMapper.entityToAthleteResponse(entity);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("message");
        }
    }

    @Override
    public AthleteTableResponse findAthleteByParams(AthleteFilterParams athleteFilterParams) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Athlete> criteriaQuery = builder.createQuery(Athlete.class);
        Root<Athlete> root = criteriaQuery.from(Athlete.class);

        List<Predicate> predicates = createPredicate(athleteFilterParams, builder, root);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Athlete> query = entityManager.createQuery(criteriaQuery);
        List<Athlete> athleteList = query.getResultList();

        var athleteResponseList = athleteList.stream().map(athleteMapper::entityToAthleteResponse).toList();
        return AthleteTableResponse.builder().athleteResponseList(athleteResponseList).build();
    }

    private List<Predicate> createPredicate(AthleteFilterParams athleteFilterParams, CriteriaBuilder builder, Root<Athlete> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(athleteFilterParams.getName())){
            predicates.add(builder.like(root.get("fullName"), "%" + athleteFilterParams.getName() + "%"));
        }
        if (StringUtils.hasText(athleteFilterParams.getSocialName())){
            predicates.add(builder.like(root.get("socialName"), "%" + athleteFilterParams.getSocialName() + "%"));
        }

        return predicates;
    }

    @Override
    public void deleteAthleteInfo(Long id) {
        var entity = verifyIfExist(id);
        athleteRepository.delete(entity);
    }

    private Athlete verifyIfExist(Long id){
        return athleteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Error locating {} in database from id {}", Athlete.class.getName(), id)));
    }
}
