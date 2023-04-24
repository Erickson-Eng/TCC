package br.com.quatty.backend.infra.config.keycloak;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeycloakLoginResponse {

    @SerializedName(value = "access_token")
    private String accessToken;
    @SerializedName(value = "expires_in")
    private String expiresIn;
    @SerializedName(value = "refresh_expires_in")
    private String refreshExpiresIn;
    @SerializedName(value = "refresh_token")
    private String refreshToken;
    @SerializedName(value = "token_type")
    private String tokenType;
    @SerializedName(value = "not-before-policy")
    private String notBeforePolicy;
    @SerializedName(value = "session_state")
    private String sessionState;
    private String scope;
}
