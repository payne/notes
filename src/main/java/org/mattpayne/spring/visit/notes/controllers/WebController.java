package org.mattpayne.spring.visit.notes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        setCurrentDate(model);
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        setCurrentDate(model);
        return "add";
    }

    @PostMapping("/add")
    public String addPost(Model model) {
        setCurrentDate(model);
        return "add";
    }


    @GetMapping("/search")
    public String search(Model model) {
        setCurrentDate(model);
        return "search";
    }

    @GetMapping("/listUrls")
    public String listUrls(Model model) {
        setCurrentDate(model);
        return "list_urls";
    }

    @GetMapping("/listTags")
    public String listTags(Model model) {
        setCurrentDate(model);
        return "list_tags";
    }

    @RequestMapping("/securedPage")
    public String securedPage(Model model, Principal principal) {
        System.out.println(principal);
        System.out.println(principal.getName());
        System.out.println(principal.toString());
        return "securedPage";
    }

    @RequestMapping("/")
    public String index(Model model, Principal principal) {
        return "index";
    }


    private void setCurrentDate(Model model) {
        String currentDate = (new Date()).toString();
        model.addAttribute("currentDate", currentDate);
    }

}
