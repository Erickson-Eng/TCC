package br.com.quatty.backend.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AthleteResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String socialName;
    private String birthDate;
    private String cpf;
    @JsonProperty("locale")
    private LocaleResponse localeResponse;

    private List<BodyMeasureResponse> bodyMeasureList;

    private String username;
}
