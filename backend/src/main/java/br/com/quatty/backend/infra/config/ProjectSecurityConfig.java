package br.com.quatty.backend.infra.config;


import br.com.quatty.backend.infra.filter.CsrfCookieFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;


@Configuration
public class ProjectSecurityConfig {

    protected static final String[] ROLES = {"ATHLETE", "ADMIN", "MANAGER"};

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(List.of("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
                }).and().csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler)
                        .ignoringRequestMatchers("/contact","/api/v1/user")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/athlete").hasAnyRole("ATHLETE", "ADMIN")
                .requestMatchers("/api/v1/locale").hasAnyRole("ATHLETE", "ADMIN", "MANAGER")
                .requestMatchers("/api/v1/booking").hasAnyRole( "ADMIN", "MANAGER", "COMMUNITY_MANAGER")
                .requestMatchers("/api/v1/community").hasAnyRole("ATHLETE", "ADMIN")
                .requestMatchers("/api/v1/gym").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers("/api/v1/membership").hasAnyRole("COMMUNITY_MANAGER", "ADMIN")
                .requestMatchers("/api/v1/practicable").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers("/api/v1/sport").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/v1/user").permitAll()
                .and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter);
        return http.build();
    }


}
