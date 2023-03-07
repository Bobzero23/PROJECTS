package com.BookStore.BookStore.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister() {
        return "bookRegister";
    }

    @GetMapping("/available_books")
    public String getAllBooks(){
        return "bookList";
    }
}
