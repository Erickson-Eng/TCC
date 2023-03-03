package br.com.quatty.backend.api.controller;

import br.com.quatty.backend.api.dto.request.CommunityRequest;
import br.com.quatty.backend.api.dto.response.CommunityResponse;
import br.com.quatty.backend.api.dto.table.CommunityTableResponse;
import br.com.quatty.backend.business.service.CommunityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/community")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommunityController {

    private CommunityService communityService;

    @PostMapping
    public ResponseEntity<CommunityResponse> createCommunity(@RequestBody @Valid CommunityRequest communityRequest){
        var community = communityService.createCommunity(communityRequest);
        URI uri = URI.create(community.getId().toString());
        return ResponseEntity.created(uri).body(community);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CommunityResponse> updateCommunityInfo(@PathVariable Long id,
                                                             @RequestBody @Valid CommunityRequest communityRequest){
        var community = communityService.updateCommunity(id, communityRequest);
        return ResponseEntity.ok().body(community);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<CommunityTableResponse> getCommunityByName(@PathVariable String name){
        var community = communityService.findCommunityByName(name);
        return ResponseEntity.ok().body(community);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommunityResponse> deleteCommunity(@PathVariable Long id){
        communityService.deleteCommunity(id);
        return ResponseEntity.noContent().build();
    }
}
