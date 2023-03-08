package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.api.dto.mapper.PracticableMapper;
import br.com.quatty.backend.api.dto.request.PracticableRequest;
import br.com.quatty.backend.api.dto.response.PracticableResponse;
import br.com.quatty.backend.business.entity.Practicable;
import br.com.quatty.backend.business.entity.Sport;
import br.com.quatty.backend.business.entity.pk.PracticablePK;
import br.com.quatty.backend.business.service.PracticableService;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.infra.repository.PracticableRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PracticableServicePostgresql implements PracticableService {

    private PracticableRepository practicableRepository;
    private PracticableMapper practicableMapper;


    @Override
    public PracticableResponse createPracticable(PracticableRequest practicableRequest) {
        var entity = practicableMapper.practicableRequestToEntity(practicableRequest);
        entity = practicableRepository.save(entity);
        return practicableMapper.entityToPracticableResponse(entity);
    }

    @Override
    public PracticableResponse updatePracticableInfo(PracticableRequest practicableRequest) {
        var entity = getPracticable(practicableRequest.getGymId(), practicableRequest.getSportId());
        entity.setPracticableState(practicableRequest.getPracticableState());
        entity = practicableRepository.save(entity);
        return practicableMapper.entityToPracticableResponse(entity);
    }

    private Practicable getPracticable(Long gymId, Long sportId){
        var key = PracticablePK.builder().gymId(gymId).sportId(sportId).build();
        return practicableRepository.findById(key)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Error locating {} in database from gymId: {}, sportId: {}", Sport.class.getName(), gymId, sportId )));
    }
}
