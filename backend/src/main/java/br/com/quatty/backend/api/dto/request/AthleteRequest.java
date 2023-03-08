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
public class AthleteRequest {

    @NotBlank(message = "Athlete name is required")
    private String fullName;
    private String socialName;
    private String birthDate;
    @NotNull(message = "The athlete's CPF is mandatory")
    private String cpf;
    @NotNull(message = "It is necessary to inform the id of the location")
    private Long localeId;
    private Double weight;
    private Double height;
    private Double bicepsMeasurement;
    private Double forearmMeasurement;
    private Double chestMeasurement;
    private Double thighMeasurement;
    private Double calfMeasurement;
}
