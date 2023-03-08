package com.BookStore.BookStore.controller;

import com.BookStore.BookStore.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyBookListController {

    @Autowired
    private MyBookService myBookService;

    @GetMapping("/deleteMyList/{id}")
    public String deleteMyList(@PathVariable("id") int id) {
        myBookService.deleteById(id);
        return "redirect:/my_books";
    }
}
