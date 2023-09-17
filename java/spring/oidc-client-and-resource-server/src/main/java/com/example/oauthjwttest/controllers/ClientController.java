package com.example.oauthjwttest.controllers;

import com.example.oauthjwttest.security.CustomAuthToken;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

// Used in client-mode
@Profile("client")
@RestController
@RequestMapping("/")
public class ClientController {
    @Profile("client")
    @CrossOrigin(origins = "http://localhost:8084")
    @GetMapping(path = "/customer")
    public String getCurrentCustomerAsClient(Principal principal) {
        return principal.getName();
    }
}
