package br.com.quatty.backend.api.controller;

import br.com.quatty.backend.business.service.MembershipService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/membership")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MembershipController {

    private MembershipService membershipService;
}
