package br.com.quatty.backend.api.dto.response.enums;

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
public class GymTypeResponse {

    @JsonProperty("gymType")
    private List<String> gymTypeList;
}
