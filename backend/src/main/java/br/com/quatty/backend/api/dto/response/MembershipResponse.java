package br.com.quatty.backend.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipResponse {

    private Long athleteId;
    private String athleteName;
    private Long communityId;
    private String communityName;
    private String applicationState;
    private String createdDate;
    private String modifiedDate;
    private String communityProfile;
}
