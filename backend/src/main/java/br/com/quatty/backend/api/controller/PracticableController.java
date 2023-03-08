package br.com.quatty.backend.api.controller;

import br.com.quatty.backend.api.dto.request.PracticableRequest;
import br.com.quatty.backend.api.dto.response.PracticableResponse;
import br.com.quatty.backend.business.service.PracticableService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/practicable")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PracticableController {

    private PracticableService practicableService;

    @PostMapping
    public ResponseEntity<PracticableResponse> createRelationBetweenSportAndGym(@RequestBody @Valid PracticableRequest practicableRequest){
        var practicable = practicableService.createPracticable(practicableRequest);
        return ResponseEntity.ok().body(practicable);
    }

    @PutMapping
    public ResponseEntity<PracticableResponse> updateRelationBetweenSportAndGym(@RequestBody PracticableRequest practicableRequest){
        var practicable = practicableService.updatePracticableInfo(practicableRequest);
        return ResponseEntity.ok().body(practicable);
    }
}
