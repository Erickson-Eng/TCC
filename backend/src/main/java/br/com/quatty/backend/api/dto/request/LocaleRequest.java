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
public class LocaleRequest {

    @NotBlank(message = "The street is a mandatory attribute")
    private String street;
    private String complement;
    @NotBlank(message = "The number is a mandatory attribute")
    private String number;
    @NotBlank(message = "The city is a mandatory attribute")
    private String city;
    @NotBlank(message = "State is a mandatory attribute")
    private String state;
    @NotBlank(message = "zip code is a mandatory attribute")
    private String zipCode;
}
