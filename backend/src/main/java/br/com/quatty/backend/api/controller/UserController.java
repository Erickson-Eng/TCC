package br.com.quatty.backend.api.controller;


import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {


    private WebClient webClient;

    @PostMapping
    public Mono<ClientResponse> createUser() {

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername("cellash");
        userRepresentation.setEmail("erickson.tulio96@gmail.com");
        userRepresentation.setFirstName("erickson");
        userRepresentation.setLastName("azevedo");
        userRepresentation.setEnabled(true);
        return webClient.post()
                .uri("http://localhost:8080/realms/quattys/account/")
                .bodyValue(userRepresentation)
                .exchangeToMono(Mono::just);
    }

}
