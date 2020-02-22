package nbu.medicaljournal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}