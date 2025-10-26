package net.mercnet.filamently.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Greetings from Spring Boot!";
    }

}
