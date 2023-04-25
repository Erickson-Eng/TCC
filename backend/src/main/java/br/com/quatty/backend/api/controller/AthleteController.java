package br.com.quatty.backend.api.controller;

import br.com.quatty.backend.api.dto.filter.AthleteFilterParams;
import br.com.quatty.backend.api.dto.request.ProfileRequest;
import br.com.quatty.backend.api.dto.response.AthleteResponse;
import br.com.quatty.backend.api.dto.table.AthleteTableResponse;
import br.com.quatty.backend.business.service.AthleteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/athlete")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AthleteController {

    private AthleteService athleteService;

    @PostMapping
    public ResponseEntity<AthleteResponse> createAthlete(@RequestBody @Valid ProfileRequest athleteRequest){
        var athlete = athleteService.createAthlete(athleteRequest);
        URI uri = URI.create(athlete.getId().toString());
        return ResponseEntity.created(uri).body(athlete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AthleteResponse> updateInfo(@RequestBody @Valid ProfileRequest athleteRequest,
                                                      @PathVariable Long id){
        var athlete = athleteService.updateAthleteInfo(id, athleteRequest);
        return ResponseEntity.ok().body(athlete);
    }

    @GetMapping
    public ResponseEntity<AthleteTableResponse> getAthletesByParams(AthleteFilterParams athleteFilterParams){
        var athleteTable = athleteService.findAthleteByParams(athleteFilterParams);
        return ResponseEntity.ok().body(athleteTable);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAthleteInfo(@PathVariable Long id){
        athleteService.deleteAthleteInfo(id);
        return ResponseEntity.noContent().build();
    }
}
