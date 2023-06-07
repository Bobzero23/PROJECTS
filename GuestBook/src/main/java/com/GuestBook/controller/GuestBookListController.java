package com.GuestBook.controller;

import com.GuestBook.model.GuestBook;
import com.GuestBook.service.GuestBookListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GuestBookListController {

    private final GuestBookListService guestBookListService;

    public GuestBookListController(GuestBookListService guestBookListService) {
        this.guestBookListService = guestBookListService;
    }


    @RequestMapping("/guestBookList")
    public String displayGuestBookList(Model model, GuestBook guestBook) {
        List<GuestBook> entries = guestBookListService.getAllEntries();
        model.addAttribute("entries", entries);
        return "guestbook-list.html";
    }

}
