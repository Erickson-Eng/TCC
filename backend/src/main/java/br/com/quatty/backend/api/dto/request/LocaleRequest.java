package br.com.quatty.backend.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocaleRequest {

    private String street;
    private String complement;
    private String number;
    private String city;
    private String state;
    private String zipCode;
}
