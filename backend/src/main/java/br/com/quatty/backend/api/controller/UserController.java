package br.com.quatty.backend.api.controller;


import br.com.quatty.backend.api.dto.request.UserRequest;
import br.com.quatty.backend.api.dto.response.UserResponse;
import br.com.quatty.backend.business.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createSport(@RequestBody @Valid UserRequest userRequest){
        UserResponse userResponse = userService.createUser(userRequest);
        URI uri = URI.create(userResponse.getId().toString());
        return ResponseEntity.created(uri).body(userResponse);
    }

    @GetMapping
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("hello world");
    }
}
