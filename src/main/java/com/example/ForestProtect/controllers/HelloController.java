package com.example.ForestProtect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @RequestMapping(value = "/",  produces={"text/html; charset=UTF-8"})
    public ModelAndView hello () {
        ModelAndView mv = new ModelAndView("hello");
        return mv;
    }
    @RequestMapping(value = "/download.html")
    public ModelAndView download(){
        ModelAndView mv = new ModelAndView("download");

        return mv;
    }
}