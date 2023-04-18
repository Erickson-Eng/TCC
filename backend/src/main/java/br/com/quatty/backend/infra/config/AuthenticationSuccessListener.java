package br.com.quatty.backend.infra.config;


import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@Slf4j
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        if (event.getAuthentication() instanceof KeycloakAuthenticationToken authentication) {
            KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal = (KeycloakPrincipal<RefreshableKeycloakSecurityContext>) authentication.getPrincipal();
            RefreshableKeycloakSecurityContext securityContext = principal.getKeycloakSecurityContext();
            String username = securityContext.getIdToken().getPreferredUsername();
            String email = securityContext.getIdToken().getEmail();
            log.info("Usuário '{}' autenticado com sucesso. E-mail: {}", username, email);
        }
    }
}
