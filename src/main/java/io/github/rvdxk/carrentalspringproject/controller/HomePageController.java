package io.github.rvdxk.carrentalspringproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

    @GetMapping("/home")
    public ResponseEntity<String> welcomePage(){
        return ResponseEntity.ok("Welcome to our car rental!");
    }
}
