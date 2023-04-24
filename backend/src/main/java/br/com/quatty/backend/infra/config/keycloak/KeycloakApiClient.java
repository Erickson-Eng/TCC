package br.com.quatty.backend.infra.config.keycloak;

import lombok.AllArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class KeycloakApiClient {

    private WebClient webClient;
    private String accessToken;

    public KeycloakApiClient(String keycloakUrl, String realm, String clientId, String clientSecret) {
        this.accessToken = obtainAccessToken(keycloakUrl, realm, clientId, clientSecret);
        this.webClient = WebClient.builder()
                .baseUrl(keycloakUrl + "/realms/" + realm + "/account/")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    private String obtainAccessToken(String keycloakUrl, String realm, String clientId, String clientSecret) {
        WebClient accessTokenWebClient = WebClient.builder()
                .baseUrl(keycloakUrl + "/realms/" + realm + "/protocol/openid-connect/token")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "client_credentials");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        return accessTokenWebClient.post()
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(AccessTokenResponse.class)
                .map(AccessTokenResponse::getToken)
                .block();
    }

}
