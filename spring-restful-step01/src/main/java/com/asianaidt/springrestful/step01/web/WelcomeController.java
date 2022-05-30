package com.asianaidt.springrestful.step01.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class WelcomeController {
    @GetMapping("/welcome")
    public String welcome() {
        System.out.println("controller");
        return "/index";
    }
}
