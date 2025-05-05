package com.example.demo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "index"; // 對應 templates/index.html
    }

    @GetMapping("/index")
    public String index() {
        return "index"; // 對應 templates/index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // 對應 templates/login.html
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // 對應 templates/register.html
    }

    @GetMapping("/activity")
    public String activity() {
        return "activity"; // 對應 templates/activity.html
    }

    @GetMapping("/reset-password")
    public String resetPassword() {
        return "reset-password"; // 對應 templates/reset-password.html
    }

    @GetMapping("/points")
    public String points() {
        return "points"; // 對應 templates/points.html
    }

}
