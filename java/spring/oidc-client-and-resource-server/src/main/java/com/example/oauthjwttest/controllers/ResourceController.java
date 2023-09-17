package com.example.oauthjwttest.controllers;

import com.example.oauthjwttest.security.CustomAuthToken;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Used in resource-server mode
@Profile("!client")
@RestController
@RequestMapping("/")
public class ResourceController {
    @CrossOrigin(origins = "http://localhost:8084")
    @GetMapping(path = "/customer")
    public String getCurrentCustomer(CustomAuthToken principal) {
        return principal.getPrincipal();
    }
}
