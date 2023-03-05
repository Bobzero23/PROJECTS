package com.rungroup.web.controller;


import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClubs(); //getting all clubs from the database
        model.addAttribute("clubs", clubs); //displaying the clubs from database to the client
        return "clubs-list";
    }
}
