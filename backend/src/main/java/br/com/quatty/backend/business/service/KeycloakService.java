package br.com.quatty.backend.business.service;

import br.com.quatty.backend.api.dto.response.AccessTokenResponseDTO;

public interface KeycloakService {

    String createUser(String username, String email, String password,
                      String firstName, String lastName, String realmRole);

    void updatePassword(String id, String password);

    void addRealmRoleToUser(String userId, String roleName);

    AccessTokenResponseDTO login(String username, String password);
}
