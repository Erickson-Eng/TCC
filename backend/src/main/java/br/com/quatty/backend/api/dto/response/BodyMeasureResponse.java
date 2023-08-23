package br.com.quatty.backend.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BodyMeasureResponse {

    private Long id;
    private Double weight;
    private Double height;
    private Double biceps;
    private Double forearm;
    private Double chest;
    private Double thigh;
    private Double calf;
}
