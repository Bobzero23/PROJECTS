package com.OnlineBookStoreMvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = {"/"})
    public String displayIndexPage() {
        return "index.html";
    }
}
