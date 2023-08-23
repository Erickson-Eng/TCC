package br.com.quatty.backend.api.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {

    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    private String socialName;
    private String birthDate;
    @CPF(message = "enter the cpf in the pattern 000.000.000-00")
    private String cpf;
    @Valid
    @JsonProperty("locale")
    private LocaleRequest localeRequest;
    private Long userId;
}
