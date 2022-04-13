package ru.geekbrains.marchmarket.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/t")
public class PageController {


    @GetMapping("/test")
    public String some(){
        return "auth-page";
    }
}
