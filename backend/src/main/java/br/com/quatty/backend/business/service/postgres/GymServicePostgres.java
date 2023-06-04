package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.api.dto.filter.GymFilterParams;
import br.com.quatty.backend.api.dto.mapper.GymMapper;
import br.com.quatty.backend.api.dto.request.GymRequest;
import br.com.quatty.backend.api.dto.request.PracticableRequest;
import br.com.quatty.backend.api.dto.response.GymResponse;
import br.com.quatty.backend.api.dto.table.GymTableResponse;

import br.com.quatty.backend.business.entity.Gym;
import br.com.quatty.backend.business.entity.Locale;
import br.com.quatty.backend.business.entity.enums.PracticableState;
import br.com.quatty.backend.business.service.GymService;
import br.com.quatty.backend.business.service.PracticableService;
import br.com.quatty.backend.business.service.SportService;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.infra.repository.GymRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GymServicePostgres implements GymService {


    private PracticableService practicableService;
    private SportService sportService;
    private GymRepository gymRepository;
    private GymMapper gymMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public GymResponse createGym(GymRequest gymRequest) {
        var entity = gymMapper.gymRequestToEntity(gymRequest);
        entity = gymRepository.save(entity);

        List<Long> sportPracticable = gymRequest.getSportPracticable();
        if (sportPracticable != null && !sportPracticable.isEmpty()){
            Gym finalEntity = entity;
            gymRequest.getSportPracticable().forEach(
                    practicable -> {
                        PracticableRequest practicableRequest = PracticableRequest.builder()
                                .practicableState(PracticableState.PRACTICABLE.getCode())
                                .gymId(finalEntity.getId())
                                .sportId(practicable)
                                .build();

                        practicableService.createPracticable(practicableRequest);
                    }
            );
        }
        return gymMapper.entityToGymResponse(entity);
    }

    @Override
    public GymResponse updateGymInfo(Long id, GymRequest gymRequest) {
        var entity = verifyIfExist(id);
        entity = gymMapper.updateGymValues(entity, gymRequest);
        entity = gymRepository.save(entity);
        return gymMapper.entityToGymResponse(entity);
    }

    @Override
    public GymTableResponse findGymByFilter(GymFilterParams gymFilterParams) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Gym> criteriaQuery = builder.createQuery(Gym.class);
        Root<Gym> root = criteriaQuery.from(Gym.class);

        List<Predicate> predicates = createPredicate(gymFilterParams, builder, root);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Gym> query = entityManager.createQuery(criteriaQuery);
        List<Gym> gymList = query.getResultList();

        var gymResponseList = gymList.stream().map(gymMapper::entityToGymResponse).toList();
        return GymTableResponse.builder().gymResponseList(gymResponseList).build();
    }

    @Transactional
    @Override
    public GymTableResponse getAllGyms() {
        List<Gym> gyms = gymRepository.findAll();
        List<GymResponse> gymResponseList = gyms.stream().map(gymMapper::entityToGymResponse).toList();
        gymResponseList.forEach(gymResponse -> gymResponse.setSports(sportService.getSportForGym(gymResponse.getId())));
        return GymTableResponse.builder().gymResponseList(gymResponseList).build();
    }



    @Override
    public GymResponse getGymById(Long id) {
        Optional<Gym> gym = gymRepository.findById(id);
        if (gym.isPresent()){
            GymResponse gymResponse = gymMapper.entityToGymResponse(gym.get());
            gymResponse.setSports(sportService.getSportForGym(id));
            return gymResponse;
        }

        return null;
    }

    private List<Predicate> createPredicate(GymFilterParams gymFilterParams,
                                            CriteriaBuilder builder,
                                            Root<Gym> root) {
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.hasText(gymFilterParams.getName())){
            predicates.add(builder.like(builder.lower(root.get("name")), "%"+ gymFilterParams.getName().toLowerCase() +"%"));
        }
        if (StringUtils.hasText(gymFilterParams.getCity())){
            Join<Gym, Locale> localeJoin = root.join("locale");
            predicates.add(builder.like(builder.lower(localeJoin.get("city")), "%" + gymFilterParams.getCity().toLowerCase() +"%"));
        }
        return predicates;
    }

    @Override
    public void deleteGym(Long id) {
        var entity = verifyIfExist(id);
        gymRepository.delete(entity);
    }

    private Gym verifyIfExist(Long id) {
        return gymRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Error locating {} in database from id {}", Gym.class.getName(), id)));
    }
}
