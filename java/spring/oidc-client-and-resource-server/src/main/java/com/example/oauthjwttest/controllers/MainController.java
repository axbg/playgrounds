package com.example.oauthjwttest.controllers;

import com.example.oauthjwttest.security.CustomAuthToken;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
    @GetMapping(path = "/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("public");
    }
}
