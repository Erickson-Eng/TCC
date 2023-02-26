package br.com.quatty.backend.api.dto.table;

import br.com.quatty.backend.api.dto.response.GymResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymTableResponse {

    @JsonProperty("gymList")
    private List<GymResponse> gymResponseList;
}
