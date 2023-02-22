package br.com.quatty.backend.infra.config.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("br.com.quatty.backend.infra.repository")
@EnableJpaAuditing(auditorAwareRef = "keycloakAuditorAware")
public class JpaConfig {
    @Bean
    public AuditorAware<String> keycloakAuditorAware() {
        return new KeycloakAuditorAware();
    }
}
