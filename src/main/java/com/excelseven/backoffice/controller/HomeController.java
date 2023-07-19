package com.excelseven.backoffice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/authentication")
    public String home() {
        return "authentication";
    }

    @GetMapping("/pass")
    public String pass() {
        return "pass";
    }
}
