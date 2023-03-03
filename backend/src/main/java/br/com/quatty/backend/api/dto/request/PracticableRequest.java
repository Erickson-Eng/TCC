package br.com.quatty.backend.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticableRequest {

    @NotNull(message = "the gym id is mandatory")
    private Long gymId;
    @NotNull(message = "the sport id is mandatory")
    private Long sportId;
    @NotNull(message = "It is necessary to inform if the sport is currently practiced")
    @JsonProperty("practicable")
    private String practicableState;
}
