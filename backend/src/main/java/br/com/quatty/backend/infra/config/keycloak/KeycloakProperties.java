package br.com.quatty.backend.infra.config.keycloak;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class KeycloakProperties {

    private final Environment env;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    public KeycloakProperties(Environment env) {
        this.env = env;
    }
    public String getServerUrl() {
        return serverUrl != null ? serverUrl : env.getProperty("keycloak.auth-server-url");
    }

    public String getRealm() {
        return realm != null ? realm : env.getProperty("keycloak.realm");
    }

    public String getClientId() {
        return clientId != null ? clientId : env.getProperty("keycloak.resource");
    }

    public String getClientSecret() {
        return clientSecret != null ? clientSecret : env.getProperty("keycloak.credentials.secret");
    }
}
