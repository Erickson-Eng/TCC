package br.com.quatty.backend.infra.config.keycloak;

import com.nimbusds.jose.shaded.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeycloakProps {

    private String uri;
    private String token;
    private String realm;
    private String server;
    private String publicKey;
    private String login;
    private String logoutUrl;
    @SerializedName(value = "client_id")
    private String clientId;
    @SerializedName(value = "client_secret")
    private String clientSecret;
    private String accountService;
    private String accountServiceSecret;

    public KeycloakProps keycloakPropsBuilder(){
        return KeycloakProps.builder()
                .server(System.getProperty("keycloakServer"))
                .realm(System.getProperty("keycloakRealm"))
                .login(System.getProperty("keycloakLogin"))
                .logoutUrl(System.getProperty("keycloakLogout"))
                .build();
    }

    public String getKeycloakUrl(){
        return getServer() + getRealm() + getLogin();
    }
}
