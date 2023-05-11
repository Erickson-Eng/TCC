package br.com.quatty.backend.api.controller;

import br.com.quatty.backend.api.dto.request.SportRequest;
import br.com.quatty.backend.api.dto.response.SportResponse;
import br.com.quatty.backend.api.dto.table.SportTableResponse;
import br.com.quatty.backend.business.service.SportService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/sport")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SportController {

    private SportService sportService;

    @PostMapping
    public ResponseEntity<SportResponse> createSport(@RequestBody @Valid SportRequest sportRequest){
        var sport = sportService.createSport(sportRequest);
        URI uri = URI.create(sport.getId().toString());
        return ResponseEntity.created(uri).body(sport);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SportResponse> updateSportInfo(@PathVariable Long id,
                                                         @RequestBody SportRequest sportRequest){
        var sport = sportService.updateSport(id, sportRequest);
        return ResponseEntity.ok().body(sport);
    }

    @GetMapping
    public ResponseEntity<SportTableResponse> getAllSports(){
        var sportTable = sportService.getAllSports();
        return ResponseEntity.ok().body(sportTable);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<SportResponse> getSportById(@PathVariable Long id){
        var sport = sportService.getSportById(id);
        return ResponseEntity.ok().body(sport);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<SportTableResponse> getSportByName(@PathVariable String name){
        var sport = sportService.getSportByName(name);
        return ResponseEntity.ok().body(sport);
    }

}
