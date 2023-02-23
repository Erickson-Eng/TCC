package br.com.quatty.backend.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticableRequest {

    private Long gymId;
    private Long sportId;
    private String practicableState;
}
