package br.com.quatty.backend.infra.config.keycloak;

import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

public class KeycloakClient {

    public WebClient webClient(ReactiveClientRegistrationRepository clientRegistrationRepository,
                        ReactiveOAuth2AuthorizedClientService clientService){
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth =
                new ServerOAuth2AuthorizedClientExchangeFilterFunction(
                        new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
                                clientRegistrationRepository, clientService
                        )
                );
        oauth.setDefaultClientRegistrationId("sportive-service");
        return WebClient.builder()
                .filter(oauth)
                .build();
    }
}
