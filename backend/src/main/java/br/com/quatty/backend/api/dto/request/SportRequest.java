package br.com.quatty.backend.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SportRequest {
    @NotBlank(message = "The name of the sport is a mandatory attribute")
    private String name;
}
