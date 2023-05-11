package br.com.quatty.backend.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityRequest {

    @NotBlank(message = "community name is a mandatory attribute")
    private String name;
    @NotBlank(message = "Community description is required")
    private String description;
    private String communityRules;
    private Long imageId;
}
