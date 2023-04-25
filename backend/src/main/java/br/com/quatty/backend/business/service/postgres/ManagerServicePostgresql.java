package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.api.dto.mapper.ProfileMapper;
import br.com.quatty.backend.api.dto.request.ProfileRequest;
import br.com.quatty.backend.api.dto.response.ManagerResponse;
import br.com.quatty.backend.business.entity.Manager;
import br.com.quatty.backend.business.service.ManagerService;
import br.com.quatty.backend.infra.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ManagerServicePostgresql implements ManagerService {

    private ManagerRepository managerRepository;
    private ProfileMapper profileMapper;
    @Override
    public ManagerResponse createManager(ProfileRequest profileRequest) {
        Manager manager = profileMapper.createManagerFromProfileDTO(profileRequest);
        manager = managerRepository.save(manager);
        return profileMapper.createResponseFromEntity(manager);
    }
}
