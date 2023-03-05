package com.definex.definex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/app")
public class AppliationController {
    @GetMapping
    public String displayPage() {
        return "/home";
    }
}
what shall I add in this code to write a spring rest api to take id number, username, surname, income, birthDate and mobileNumber from the user and add them to data base
        using java