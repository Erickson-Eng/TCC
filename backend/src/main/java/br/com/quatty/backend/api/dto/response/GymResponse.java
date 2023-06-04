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
public class GymResponse {

    private Long id;
    private String name;
    private String rules;
    private Long managerId;
    @JsonProperty("locale")
    private LocaleResponse localeResponse;
    private String shortDescription;
    private List<SportResponse> sports;
    private Long imageId;
}
