package br.com.quatty.backend.api.controller;

import br.com.quatty.backend.business.entity.Athlete;
import br.com.quatty.backend.business.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private AthleteService athleteService;




    @PostMapping("/hello")
    public String hello(@RequestBody Athlete athlete){
        athleteService.create(athlete);
        return "Hello world";
    }
}
