package br.com.quatty.backend.infra.config.audit;


import br.com.quatty.backend.infra.config.ThreadLocalHolder;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.StringUtils;


import java.util.Optional;

@Slf4j
public class KeycloakAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String auditor = null;
        Authentication authentication = ThreadLocalHolder.getAuthentication();
        if (authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if (principal instanceof KeycloakPrincipal){
                KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
                KeycloakSecurityContext keycloakSecurityContext = keycloakPrincipal.getKeycloakSecurityContext();
                String username = keycloakSecurityContext.getIdToken().getPreferredUsername();
                String email = keycloakSecurityContext.getIdToken().getEmail();
                auditor = StringUtils.hasText(email) ? username : email;
            } else if (principal instanceof Jwt) {
                Jwt jwt = (Jwt) principal;
                String username = jwt.getClaim("preferred_username");
                String email = jwt.getClaim("email");
                auditor = StringUtils.hasText(username) ? username : email;
            } else {
                log.warn("O tipo do objeto principal não é reconhecido: {}", principal.getClass());
            }
        }
        return Optional.ofNullable(auditor);
    }
}

