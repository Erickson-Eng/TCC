package br.com.quatty.backend.api.controller;

import br.com.quatty.backend.api.dto.request.MembershipRequest;
import br.com.quatty.backend.api.dto.response.MembershipResponse;
import br.com.quatty.backend.business.entity.enums.ApplicationState;
import br.com.quatty.backend.business.entity.enums.CommunityProfile;
import br.com.quatty.backend.business.service.MembershipService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/membership")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MembershipController {

    private MembershipService membershipService;

    @PostMapping
    public ResponseEntity<MembershipResponse > registerApplication(@RequestBody @Valid MembershipRequest request){

        MembershipRequest membershipRequest = MembershipRequest
                .builder()
                .athleteId(request.getAthleteId())
                .communityId(request.getCommunityId())
                .applicationState(ApplicationState.WAITING_FOR_APPROVAL.getCode())
                .communityProfile(CommunityProfile.ATHLETE.getCode())
                .build();
        MembershipResponse response = membershipService.applyOnCommunity(membershipRequest);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<MembershipResponse> updateApplication(@RequestBody @Valid MembershipRequest request){
        if (!StringUtils.hasText(request.getApplicationState()) ||
                !StringUtils.hasText(request.getCommunityProfile())){
            return ResponseEntity.badRequest().build();
        }
        MembershipResponse response = membershipService.updateMembershipInfo(request);
        return ResponseEntity.ok().body(response);
    }
}
