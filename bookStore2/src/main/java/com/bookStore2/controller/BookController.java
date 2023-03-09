package com.bookStore2.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class BookController {

    @GetMapping(value = {"/", "home", "/home"})
    public String displayHomePage(){
        return "home";
    }
}
