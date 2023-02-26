package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.api.dto.filter.LocaleFilterParams;
import br.com.quatty.backend.api.dto.mapper.LocaleMapper;
import br.com.quatty.backend.api.dto.request.LocaleRequest;
import br.com.quatty.backend.api.dto.response.LocaleResponse;
import br.com.quatty.backend.api.dto.table.LocaleTableResponse;
import br.com.quatty.backend.business.entity.Locale;
import br.com.quatty.backend.business.service.LocaleService;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.infra.repository.LocaleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LocaleServicePostgresql implements LocaleService {

    private LocaleRepository localeRepository;
    private LocaleMapper localeMapper;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public LocaleResponse createLocale(LocaleRequest localeRequest) {
        var entity = localeMapper.localeRequestToEntity(localeRequest);
        entity = localeRepository.save(entity);
        return localeMapper.entityToLocaleResponse(entity);
    }

    @Override
    public LocaleResponse updateLocale(Long id, LocaleRequest localeRequest) {
        var entity = verifyIfExist(id);
        entity = localeMapper.updateLocaleValues(entity, localeRequest);
        entity = localeRepository.save(entity);
        return localeMapper.entityToLocaleResponse(entity);
    }

    @Override
    public LocaleResponse getLocaleById(Long id) {
        var entity = verifyIfExist(id);
        return localeMapper.entityToLocaleResponse(entity);
    }

    @Override
    public LocaleTableResponse getLocaleByFilter(LocaleFilterParams filterParams) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Locale> criteriaQuery = builder.createQuery(Locale.class);
        Root<Locale> root = criteriaQuery.from(Locale.class);
        List<Predicate> predicates = createPredicate(filterParams.getStreet(), filterParams.getCity(),
                filterParams.getState(), builder, root);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Locale> query = entityManager.createQuery(criteriaQuery);
        List<Locale> locales = query.getResultList();

        var localeResponseList = locales.stream().map(localeMapper::entityToLocaleResponse).toList();
        return LocaleTableResponse.builder().localeResponseList(localeResponseList).build();
    }

    private List<Predicate> createPredicate(String street, String city, String state,
                                            CriteriaBuilder builder, Root<Locale> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(street)){
            predicates.add(builder.like(root.get("street"), "%" + street + "%"));
        }
        if (StringUtils.hasText(city)){
            predicates.add(builder.like(root.get("city"), "%" + city + "%"));
        }
        if (StringUtils.hasText(state)){
            predicates.add(builder.like(root.get("state"), "%" + state + "%"));
        }
        return predicates;
    }

    private Locale verifyIfExist(Long id){
        return localeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Error locating {} in database from id {}", Locale.class.getName(), id)));
    }
}
