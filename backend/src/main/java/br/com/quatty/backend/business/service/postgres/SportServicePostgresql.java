package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.api.dto.mapper.SportMapper;
import br.com.quatty.backend.api.dto.request.SportRequest;
import br.com.quatty.backend.api.dto.response.SportResponse;
import br.com.quatty.backend.api.dto.table.SportTableResponse;
import br.com.quatty.backend.business.entity.Sport;
import br.com.quatty.backend.business.service.SportService;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.infra.repository.SportRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SportServicePostgresql implements SportService {

    private SportRepository sportRepository;
    private SportMapper sportMapper;

    @Override
    public SportResponse createSport(SportRequest sportRequest) {
        var entity = sportMapper.sportRequestToEntity(sportRequest);
        entity = sportRepository.save(entity);
        return sportMapper.entityToSportResponse(entity);
    }

    @Override
    public SportResponse updateSport(Long id, SportRequest sportRequest) {
        var entity = verifyIfExist(id);
        entity = sportMapper.updateSportValues(entity, sportRequest);
        entity = sportRepository.save(entity);
        return sportMapper.entityToSportResponse(entity);
    }

    @Override
    public SportResponse getSportById(Long id) {
        var entity = verifyIfExist(id);
        return sportMapper.entityToSportResponse(entity);
    }

    @Override
    public SportTableResponse getSportByName(String name) {
        var entityList = verifyIfExist(name);
        List<SportResponse> responses = entityList.stream().map(sportMapper::entityToSportResponse).toList();
        return SportTableResponse.builder().sportResponseList(responses).build();
    }

    @Override
    public SportTableResponse getAllSports() {
        List<SportResponse> responses = sportRepository.findAll().stream().map(sportMapper::entityToSportResponse).toList();
        return SportTableResponse.builder().sportResponseList(responses).build();
    }


    private Sport verifyIfExist(Long id) {
        return sportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Error locating {} in database from id {}", Sport.class.getName(), id)));
    }

    private List<Sport> verifyIfExist(String name){
        return sportRepository.findAllByName(name);
    }

}
