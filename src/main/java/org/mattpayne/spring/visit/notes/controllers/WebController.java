package org.mattpayne.spring.visit.notes.controllers;

import lombok.extern.log4j.Log4j2;
import org.mattpayne.spring.visit.notes.dto.TagDTO;
import org.mattpayne.spring.visit.notes.dto.UrlDTO;
import org.mattpayne.spring.visit.notes.entity.Tag;
import org.mattpayne.spring.visit.notes.service.UrlService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
public class WebController {

    private UrlService urlService;

    public WebController(UrlService urlService) {
        this.urlService = urlService;
        Resource resource = new ClassPathResource("authorized-usernames.txt");
        try {
            InputStream resourceInputStream = resource.getInputStream();
            LineNumberReader in = new LineNumberReader(new InputStreamReader(resourceInputStream));
            String line=null;
            while (null != (line=in.readLine())) {
                System.out.println(line);
            }
            in.close();
            System.out.println("^^^^ authorized-usernames.txt  ^^^^");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/")
    public String index(Model model) {
        setCurrentDate(model);
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model, UrlDTO urlDTO) {
        // model.addAttribute("urlDTO",urlDTO); // Not needed - spring magic :-)
        setCurrentDate(model);
        return "add";
    }

    // Reference: https://github.com/spring-guides/gs-validating-form-input
    @PostMapping("/add")
    public String addPost(Model model, @Valid UrlDTO urlDTO, BindingResult bindingResult) {
        setCurrentDate(model);
        Long addedId = urlService.addUrl(urlDTO);
        UrlDTO addedUrl = urlService.findUrlById(addedId);
        List<UrlDTO> urls = new ArrayList<>();
        urls.add(addedUrl);
        model.addAttribute("urls",urls);
        return "add_confirmation";
    }


    @GetMapping("/searchTags")
    public String search(@RequestParam(required=false) String tagSearch, Model model) {
        // e.g. http://localhost:8080/searchTags?tagSearch=angular,youtube
        List<TagDTO> tags = urlService.findUrlsByTags(tagSearch);
        model.addAttribute("tags",tags);
        setCurrentDate(model);
        return "list_tags";
    }

    @GetMapping("/listUrls")
    public String listUrls(Model model) {
        setCurrentDate(model);
        model.addAttribute("urls", urlService.findAllUrls());
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
            String login = (String) attributes.get("login");
            String name = (String) attributes.get("name");
            String email = (String) attributes.get("email");
            model.addAttribute("login", login);
            model.addAttribute("name", name);
            model.addAttribute("email", email);
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
