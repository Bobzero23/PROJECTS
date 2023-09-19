package com.AuthenticatedBackend.controller;

import com.AuthenticatedBackend.dto.RegistrationDto;
import com.AuthenticatedBackend.model.ApplicationUser;
import com.AuthenticatedBackend.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDto body) {
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }
}
