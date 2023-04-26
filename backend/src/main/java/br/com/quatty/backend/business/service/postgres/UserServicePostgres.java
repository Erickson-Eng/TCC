package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.api.dto.mapper.LocaleMapper;
import br.com.quatty.backend.api.dto.mapper.UserMapper;
import br.com.quatty.backend.api.dto.request.UserRequest;
import br.com.quatty.backend.api.dto.response.LocaleResponse;
import br.com.quatty.backend.api.dto.response.UserResponse;
import br.com.quatty.backend.business.entity.Locale;
import br.com.quatty.backend.business.entity.User;
import br.com.quatty.backend.business.service.*;
import br.com.quatty.backend.business.service.exception.DatabaseViolationException;
import br.com.quatty.backend.infra.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServicePostgres implements UserService {

    private KeycloakService keycloakService;
    private ManagerService managerService;
    private AthleteService athleteService;
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest userRequest) throws IllegalArgumentException {

        try {
            if (Boolean.FALSE.equals(findUserByEmail(userRequest.getEmail()).isPresent())
                    && Boolean.FALSE.equals(findUserByUsername(userRequest.getUsername()).isPresent())) {
                String keycloakId = keycloakService.createUser(
                        userRequest.getUsername(), userRequest.getEmail(), userRequest.getPassword(),
                        userRequest.getProfileRequest().getFirstName(), userRequest.getProfileRequest().getLastName(), "ATHLETE"
                );
                User user = userMapper.userRequestToEntity(userRequest);
                if (keycloakId != null) {
                    user.setKeycloakId(UUID.fromString(keycloakId));
                    user.setEnable(Boolean.TRUE);
                }
                user = userRepository.save(user);


                if (userRequest.getProfileType().equalsIgnoreCase("Athlete")) {
                    userRequest.getProfileRequest().setUserId(user.getId());
                    athleteService.createAthlete(userRequest.getProfileRequest());
                } else if (userRequest.getProfileType().equalsIgnoreCase("Manager")) {
                    userRequest.getProfileRequest().setUserId(user.getId());
                    managerService.createManager(userRequest.getProfileRequest());
                }
                return userMapper.entityToUserResponse(user);
            } else {
                throw new IllegalArgumentException("Email ou username já se encontra em uso");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseViolationException(MessageFormat.format("Ocorreu um erro ao salvar. {}", e.getMessage()));
        }

    }

    @Override
    public void updateUser(Long id, UserRequest userRequest) {
        //nao implementado
    }

    private Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
