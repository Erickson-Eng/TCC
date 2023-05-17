package br.com.quatty.backend.api.controller;


import br.com.quatty.backend.api.dto.response.ManagerResponse;
import br.com.quatty.backend.business.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/manager")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ManagerController {

    private ManagerService managerService;

    @GetMapping
    public ResponseEntity<ManagerResponse> getManagerByEmail(String email){
        ManagerResponse managerResponse = managerService.getManagerByEmail(email);
        return ResponseEntity.ok(managerResponse);
    }
}
