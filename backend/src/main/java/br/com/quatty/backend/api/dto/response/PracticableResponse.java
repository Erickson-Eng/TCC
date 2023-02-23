package br.com.quatty.backend.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticableResponse {


    private Long gymId;
    private String gymName;
    @JsonProperty("sport")
    private SportResponse sportResponse;
    private String practicableState;
}
