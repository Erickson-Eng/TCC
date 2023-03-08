package br.com.quatty.backend.api.controller;

import br.com.quatty.backend.api.dto.filter.GymFilterParams;
import br.com.quatty.backend.api.dto.request.GymRequest;
import br.com.quatty.backend.api.dto.response.GymResponse;
import br.com.quatty.backend.api.dto.table.GymTableResponse;
import br.com.quatty.backend.business.service.GymService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/gym")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GymController {

    private GymService gymService;

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping
    public ResponseEntity<GymResponse> createGym(@RequestBody @Valid GymRequest gymRequest){
        var gym  = gymService.createGym(gymRequest);
        URI uri = URI.create(gym.getId().toString());
        return ResponseEntity.created(uri).body(gym);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PutMapping("/{id}")
    public ResponseEntity<GymResponse> updateGymInfo(@PathVariable Long id,
                                                     @RequestBody GymRequest gymRequest){
        var gym = gymService.updateGymInfo(id, gymRequest);
        return ResponseEntity.ok().body(gym);
    }

    @GetMapping
    public ResponseEntity<GymTableResponse> findGymByFilter(GymFilterParams gymFilterParams){
        var gymList = gymService.findGymByFilter(gymFilterParams);
        return ResponseEntity.ok().body(gymList);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGymById(@PathVariable Long id){
        gymService.deleteGym(id);
        return ResponseEntity.noContent().build();
    }
}
