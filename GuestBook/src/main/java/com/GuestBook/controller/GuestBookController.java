package com.GuestBook.controller;

import com.GuestBook.model.GuestBook;
import com.GuestBook.service.GuestBookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestBookController {

    private final GuestBookService bookGuestService;

    public GuestBookController(GuestBookService bookGuestService) {
        this.bookGuestService = bookGuestService;
    }

    @RequestMapping(value = {"", "/", "/home", "/form"})
    public String displayIndexPage(Model model) {
        model.addAttribute("bookGuest", new GuestBook());
        return "guestbook.html";
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute GuestBook bookGuest) {
        bookGuestService.saveGuestBook(bookGuest);
        return "redirect:/form";
    }
}
