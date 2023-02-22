package br.com.quatty.backend.infra.config;


import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        if (event.getAuthentication() instanceof KeycloakAuthenticationToken) {
            KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) event.getAuthentication();
            KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal = (KeycloakPrincipal<RefreshableKeycloakSecurityContext>) authentication.getPrincipal();
            String preferredUsername = principal.getKeycloakSecurityContext().getIdToken().getPreferredUsername();
            String email = principal.getKeycloakSecurityContext().getIdToken().getEmail();
            log.info("Usuário '{}' autenticado com sucesso", preferredUsername);

        }
    }
}
