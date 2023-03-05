package com.BookStore.BookStore.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
