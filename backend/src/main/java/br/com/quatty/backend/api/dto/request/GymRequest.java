package br.com.quatty.backend.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymRequest {

    @NotBlank(message = "the gym name is a mandatory attribute")
    private String name;
    private String rules;
    @NotNull(message = "It is necessary to inform the id of the location")
    private Long localeId;
}
