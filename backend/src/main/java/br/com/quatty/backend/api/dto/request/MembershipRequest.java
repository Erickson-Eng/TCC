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

    @NotNull
    private Long athleteId;
    @NotNull
    private Long communityId;

    private String membershipStatus;
}
