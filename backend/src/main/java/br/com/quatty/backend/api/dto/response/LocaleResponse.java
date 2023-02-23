package br.com.quatty.backend.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocaleResponse {

    private Long id;
    private String street;
    private String complement;
    private String number;
    private String city;
    private String state;
    private String zipCode;
}
