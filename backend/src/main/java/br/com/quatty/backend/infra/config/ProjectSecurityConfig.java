package br.com.quatty.backend.infra.config;


import br.com.quatty.backend.infra.filter.CsrfCookieFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
public class ProjectSecurityConfig {

    private final Environment environment;
    protected static final String ROLE_ATHLETE = "ATHLETE";
    protected static final String ROLE_ADMIN = "ADMIN";
    protected static final String ROLE_MANAGER = "MANAGER";
    protected static final String COMMUNITY_MANAGER = "COMMUNITY_MANAGER";
    private static final String[] PUBLIC_MATCHERS = {
            "/api/v1/sport/**",
            "/api/v1/locale/**",
            "/api/v1/gym/**",
            "/api/v1/membership/**",
            "/api/v1/user",
            "/api/v1/image"

    };

    @Autowired
    public ProjectSecurityConfig(Environment environment) {
        this.environment = environment;
    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        if(Arrays.asList(environment.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("*"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(List.of("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
                })
                .and()
                .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler)
                        .ignoringRequestMatchers("/contact","/api/v1/user")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/athlete/**").hasAnyRole(ROLE_ATHLETE, ROLE_ADMIN)
                .requestMatchers("/api/v1/booking/**").hasAnyRole( ROLE_ADMIN, ROLE_MANAGER, COMMUNITY_MANAGER)
                .requestMatchers("/api/v1/community/**").hasAnyRole(ROLE_ATHLETE, ROLE_ADMIN)
                .requestMatchers("/api/v1/gym/**").hasAnyRole(ROLE_MANAGER, ROLE_ADMIN)
                .requestMatchers("/api/v1/practicable/**").hasAnyRole(ROLE_MANAGER, ROLE_ADMIN)
                .requestMatchers(HttpMethod.PUT,"/api/v1/membership/**").hasAnyRole(COMMUNITY_MANAGER, ROLE_ADMIN)
                .requestMatchers(HttpMethod.POST,"/api/v1/sport").hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.POST, "/api/v1/locale").hasAnyRole(ROLE_ATHLETE, ROLE_ADMIN, ROLE_MANAGER)
                .requestMatchers(HttpMethod.POST, "/api/v1/image").hasAnyRole(ROLE_ATHLETE, ROLE_ADMIN, ROLE_MANAGER)
                .requestMatchers(HttpMethod.GET, "/api/v1/image/**").hasAnyRole(ROLE_ATHLETE, ROLE_ADMIN, ROLE_MANAGER)
                .requestMatchers(HttpMethod.PUT, "/api/v1/locale/**").hasAnyRole(ROLE_ATHLETE, ROLE_ADMIN, ROLE_MANAGER)
                .requestMatchers(HttpMethod.PUT, "/api/v1/sport/**").hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS).permitAll()
                .requestMatchers(HttpMethod.POST,"/api/v1/user", "/api/v1/membership/**").permitAll()
                .and()
                .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(jwtAuthenticationConverter);

        return http.build();
    }



}
