package com.example.ForestProtect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @RequestMapping(value = "/hello.html")
    public ModelAndView hello () {
        ModelAndView mv = new ModelAndView("hello");
        String baba = "baba";
        return mv;
    }
}