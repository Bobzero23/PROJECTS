package com.GuestBook.controller;

import com.GuestBook.model.BookGuest;
import com.GuestBook.service.BookGuestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookGuestController {

    private final BookGuestService bookGuestService;

    public BookGuestController(BookGuestService bookGuestService) {
        this.bookGuestService = bookGuestService;
    }

    @RequestMapping(value = {"", "/", "/home", "/form"})
    public String displayIndexPage(Model model) {
        model.addAttribute("bookGuest", new BookGuest());
        return "book-guest.html";
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute BookGuest bookGuest) {
        bookGuestService.saveGuestBook(bookGuest);
        return "redirect:/form";
    }
}
