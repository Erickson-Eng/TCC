package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.api.dto.mapper.ProfileMapper;
import br.com.quatty.backend.api.dto.request.ProfileRequest;
import br.com.quatty.backend.api.dto.response.ManagerResponse;
import br.com.quatty.backend.business.entity.Manager;
import br.com.quatty.backend.business.service.ImageService;
import br.com.quatty.backend.business.service.LocaleService;
import br.com.quatty.backend.business.service.ManagerService;
import br.com.quatty.backend.business.service.SportService;
import br.com.quatty.backend.infra.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ManagerServicePostgresql implements ManagerService {

    private ManagerRepository managerRepository;
    private SportService sportService;
    private LocaleService localeService;
    private ImageService imageService;
    private ProfileMapper profileMapper;
    @Override
    public ManagerResponse createManager(ProfileRequest profileRequest) {
        Manager manager = profileMapper.createManagerFromProfileDTO(profileRequest);
        manager = managerRepository.save(manager);
        return profileMapper.createResponseFromEntity(manager);
    }

    @Override
    public ManagerResponse getManagerByEmail(String email) {
        Optional<Manager> manager =  managerRepository.getManagerByEmail(email);
        ManagerResponse managerResponse = manager.map(value -> profileMapper.createResponseFromEntity(value)).orElse(null);
        if (managerResponse != null){
            managerResponse.getGymResponseList().forEach(gymResponse -> {
                gymResponse.setSports(sportService.getSportForGym(gymResponse.getId()));
            });
        }
        return managerResponse;
    }
}
