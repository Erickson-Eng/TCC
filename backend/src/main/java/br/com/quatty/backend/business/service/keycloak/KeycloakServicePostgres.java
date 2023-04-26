package br.com.quatty.backend.business.service.keycloak;

import br.com.quatty.backend.business.service.KeycloakService;
import br.com.quatty.backend.infra.config.keycloak.KeycloakProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
public class KeycloakServicePostgres implements KeycloakService {

    private final Keycloak keycloak;

    private final KeycloakProperties keycloakProperties;

    public KeycloakServicePostgres(KeycloakProperties keycloakProperties) {
        this.keycloakProperties = keycloakProperties;
        keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.getServerUrl())
                .realm(keycloakProperties.getRealm())
                .clientId(keycloakProperties.getClientId())
                .clientSecret(keycloakProperties.getClientSecret())
                .grantType("client_credentials")
                .build();
    }

    @Override
    public String createUser(String username, String email, String password,
                             String firstName, String lastName, String realmRole) {
        UserRepresentation representation = createUserRepresentation(username, email, password, firstName, lastName, realmRole);
        try (Response response = keycloak.realm(keycloakProperties.getRealm()).users().create(representation)) {
            if (response.getStatus() == 201){
                return response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            } else {
                return null;
            }

        } catch (BadRequestException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updatePassword(String id, String password) {
        CredentialRepresentation credentialRepresentation = createPasswordCredential(password);
        keycloak.realm(keycloakProperties.getRealm()).users().get(id).resetPassword(credentialRepresentation);
    }

    private UserRepresentation createUserRepresentation(String username, String email, String password,
                                                        String firstName, String lastName, String realmRole) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setCredentials(List.of(
                createPasswordCredential(password)
        ));
        user.setEnabled(true);
        user.setRealmRoles(List.of(realmRole));
        return user;
    }

    private CredentialRepresentation createPasswordCredential(String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);
        return credential;
    }
}
