package br.com.quatty.backend.business.service.keycloak;

import br.com.quatty.backend.infra.config.keycloak.KeycloakProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

@Service
public class KeycloakService {

    private final Keycloak keycloak;

    private final KeycloakProperties keycloakProperties;

    public KeycloakService(KeycloakProperties keycloakProperties) {
        this.keycloakProperties = keycloakProperties;
        keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.getServerUrl())
                .realm(keycloakProperties.getRealm())
                .clientId(keycloakProperties.getClientId())
                .clientSecret(keycloakProperties.getClientSecret())
                .grantType("client_credentials")
                .build();
    }

    public String createNewUser(){
        UserRepresentation user = new UserRepresentation();
        user.setUsername("cellash");
        user.setEmail("cellashtulio@gmail.com");
        user.setFirstName("erickson");
        user.setLastName("tulio");


        Response response = keycloak.realm(keycloakProperties.getRealm()).users().create(user);
        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
        return userId;

    }
}
