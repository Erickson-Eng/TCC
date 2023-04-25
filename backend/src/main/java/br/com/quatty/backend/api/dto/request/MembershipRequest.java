package br.com.quatty.backend.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipRequest {

    @NotNull(message = "athlete id is mandatory")
    private Long athleteId;
    @NotNull(message = "community id is mandatory")
    private Long communityId;
    private String applicationState;
}
