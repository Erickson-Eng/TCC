package br.com.quatty.backend.api.controller;


import br.com.quatty.backend.business.service.keycloak.KeycloakService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final KeycloakService keycloakService;

    @PostMapping
    public ResponseEntity<String> createSport(){
        return ResponseEntity.ok(keycloakService.createNewUser());
    }
}
