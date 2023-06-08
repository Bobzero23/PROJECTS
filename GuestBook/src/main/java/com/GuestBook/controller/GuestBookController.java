package com.GuestBook.controller;

import com.GuestBook.model.GuestBook;
import com.GuestBook.service.GuestBookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class GuestBookController {

    private final GuestBookService bookGuestService;

    public GuestBookController(GuestBookService bookGuestService) {
        this.bookGuestService = bookGuestService;
    }

    @RequestMapping(value = {"", "/", "/home", "/form"})
    public String displayIndexPage(Model model) {
        model.addAttribute("guestBook", new GuestBook());
        return "guestbook.html";
    }

    @PostMapping("/submitForm")
    public String submitForm(@Valid @ModelAttribute GuestBook guestBook, Errors errors) {
        if (errors.hasErrors()) {
            log.error("failed to submit the form due to: " +  errors.toString());
            return "guestbook.html";
        }
        bookGuestService.saveGuestBook(guestBook);
        return "redirect:/form";
    }
}
