package com.UserManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String displayIndexPage() {
        return "index";
    }

    @GetMapping("/register")
    public String displayRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    public String displayLoginPage() {
        return "login";
    }
}
