package org.mattpayne.spring.visit.notes.controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/example")
    public String example(Model model) {
        setCurrentDate(model);
        return "example";
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
        System.out.println(principal.getClass().getName());
        try {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            Map<String, Object> attributes = token.getPrincipal().getAttributes();
            System.out.println(attributes);
            String login= (String) attributes.get("login");
            String name = (String) attributes.get("name");
            String email = (String) attributes.get("email");
            model.addAttribute("login",login);
            model.addAttribute("name",name);
            model.addAttribute("email",email);
        } catch (Exception e) {
            e.printStackTrace(); //TODO: use a logger!
        }
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
