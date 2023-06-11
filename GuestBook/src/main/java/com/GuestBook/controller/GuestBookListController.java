package com.GuestBook.controller;

import com.GuestBook.model.GuestBook;
import com.GuestBook.service.GuestBookListService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class GuestBookListController {
    private final GuestBookListService guestBookListService;

    public GuestBookListController(GuestBookListService guestBookListService) {
        this.guestBookListService = guestBookListService;
    }


    @RequestMapping("/guestBookList")
    public String displayGuestBookList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<GuestBook> guestBooks = guestBookListService.getAllGuestBooks(pageable);

        int totalPages = guestBooks.getTotalPages();
        if (page > totalPages) {
            return "redirect:/guestBookList?page=" + totalPages;
        }

        model.addAttribute("guestBooks", guestBooks);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "guestbook-list.html";
    }


    @RequestMapping("/deleteGuestBook/{id}")
    public String deleteGuestBookById(@PathVariable("id") int id, RedirectAttributes redirectAttributes,
                                      @RequestParam(value = "page", defaultValue = "1") int page) {
        guestBookListService.deleteGuestBook(id);
        redirectAttributes.addAttribute("page", page > 0 ? page : 1);
        return "redirect:/guestBookList";
    }

}
