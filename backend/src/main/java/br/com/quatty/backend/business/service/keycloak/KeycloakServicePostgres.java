package br.com.quatty.backend.business.service.keycloak;

import br.com.quatty.backend.api.dto.response.AccessTokenResponseDTO;
import br.com.quatty.backend.business.service.KeycloakService;
import br.com.quatty.backend.infra.config.ThreadLocalHolder;
import br.com.quatty.backend.infra.config.keycloak.KeycloakProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

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

    @Override
    public void addRealmRoleToUser(String userId, String roleName) {
        UserResource userResource = keycloak.realm(keycloakProperties.getRealm()).users().get(userId);
        List<RoleRepresentation> representations = keycloak.realms().realm(keycloakProperties.getRealm()).roles().list();
        List<RoleRepresentation> roles = representations.stream().filter(role -> role.getName().equalsIgnoreCase(roleName)).toList();
        userResource.roles().realmLevel().add(roles);
    }

    public AccessTokenResponseDTO login(String username, String password){
        String url = "http://keycloak:8080/realms/quattys/protocol/openid-connect/token";
        String clientId = "sportive";
        String grantType = "password";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("username", username);
        params.add("password", password);
        params.add("grant_type", grantType);

        // Criar a entidade HttpEntity com os cabeçalhos e parâmetros
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        // Enviar a requisição e receber a resposta
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Verificar se a requisição foi bem-sucedida e obter a resposta
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String response = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            AccessTokenResponseDTO accessTokenResponseDTO = new AccessTokenResponseDTO();
            try {
                accessTokenResponseDTO = objectMapper.readValue(response, AccessTokenResponseDTO.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            return accessTokenResponseDTO;
        } else {
            System.out.println("Falha na chamada. Código de status: " + responseEntity.getStatusCodeValue());
            return AccessTokenResponseDTO.builder().build();
        }
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
        if (!isPasswordStrong(password)){
            throw new IllegalArgumentException("The password does not follow the rules.");
        }
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);
        return credential;
    }

    protected boolean isPasswordStrong(String password) {
        String pattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%!]).{8,}";
        return password.matches(pattern);
    }

    public static String getCurrentUserId(){
        String user = null;
        Authentication authentication = ThreadLocalHolder.getAuthentication();
        if (authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if (principal instanceof KeycloakPrincipal<?>){
                KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>)  principal;
                KeycloakSecurityContext keycloakSecurityContext = keycloakPrincipal.getKeycloakSecurityContext();
                String username = keycloakSecurityContext.getIdToken().getPreferredUsername();
                user = StringUtils.hasText(username)? username : null;
            } else if (principal instanceof Jwt jwt){
                String username = jwt.getClaimAsString("preferred_username");
                user = StringUtils.hasText(username) ? username : null;
            }
        }
        return user;
    }
}
