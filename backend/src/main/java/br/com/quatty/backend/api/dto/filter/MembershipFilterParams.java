package br.com.quatty.backend.api.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipFilterParams {

    private Long athleteId;
    private Long communityId;
    private String membershipStatus;
}
