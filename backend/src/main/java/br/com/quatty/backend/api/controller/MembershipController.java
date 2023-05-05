package br.com.quatty.backend.api.controller;

import br.com.quatty.backend.api.dto.filter.MembershipFilterParams;
import br.com.quatty.backend.api.dto.request.MembershipRequest;
import br.com.quatty.backend.api.dto.response.MembershipResponse;
import br.com.quatty.backend.api.dto.response.enums.MembershipStatusResponse;
import br.com.quatty.backend.api.dto.table.MembershipTableResponse;
import br.com.quatty.backend.business.entity.enums.ApplicationState;
import br.com.quatty.backend.business.entity.enums.CommunityProfile;
import br.com.quatty.backend.business.service.MembershipService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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

    @GetMapping("/filter")
    public ResponseEntity<MembershipTableResponse> findMembershipByFilter(
            @RequestParam(name = "athleteId", required = false) Long athleteId,
            @RequestParam(name = "communityId", required = false) Long communityId,
            @RequestParam(name = "applicationState", required = false) String applicationState,
            @RequestParam(name = "sortField", required = false) String sortField,
            @RequestParam(name = "sortOrder", required = false) String sortOrder,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        if (athleteId == null && communityId == null && applicationState == null && sortField == null && sortOrder == null) {
            return ResponseEntity.badRequest().build();
        }
        MembershipFilterParams membershipFilterParams = new MembershipFilterParams();
        membershipFilterParams.setAthleteId(athleteId);
        membershipFilterParams.setCommunityId(communityId);
        membershipFilterParams.setApplicationState(applicationState);
        membershipFilterParams.setSortField(sortField);
        membershipFilterParams.setSortOrder(sortOrder);
        membershipFilterParams.setPage(page);
        membershipFilterParams.setSize(size);

        MembershipTableResponse membershipTableResponse = membershipService.findMembershipByFilter(membershipFilterParams);
        return ResponseEntity.ok(membershipTableResponse);
    }

    @GetMapping("/get-params")
    public ResponseEntity<MembershipStatusResponse> getStatusAndProfilesForMembership(){
        MembershipStatusResponse response = MembershipStatusResponse.builder()
                .applicationStatusList(ApplicationState.getAllStatus())
                .communityProfileList(CommunityProfile.getAllCommunityProfiles())
                .build();

        return ResponseEntity.ok(response);

    }
}
