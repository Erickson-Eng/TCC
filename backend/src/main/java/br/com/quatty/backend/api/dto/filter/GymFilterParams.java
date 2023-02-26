package br.com.quatty.backend.api.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymFilterParams {

    private String name;
    private String city;
    private String sportPracticable;
}
