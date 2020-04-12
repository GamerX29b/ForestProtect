package com.example.ForestProtect.controllers;

import com.sun.javafx.util.Logging;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

@Controller
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/",  produces={"text/html; charset=UTF-8"})
    private ModelAndView hello () throws InterruptedException {
        ModelAndView mv = new ModelAndView("hello");

        return mv;
    }
    @RequestMapping(value = "/view.html",  produces={"text/html; charset=UTF-8"})
    private ModelAndView view () throws InterruptedException {
        ModelAndView mv = new ModelAndView("view");

        return mv;
    }

    @RequestMapping(value = "/download.html")
    private ModelAndView download(){
        ModelAndView mv = new ModelAndView("download");

        return mv;
    }
    @RequestMapping(value = "/ImageVerification.html")
    private ModelAndView verification(){
        ModelAndView mv = new ModelAndView("ImageVerification");

        return mv;
    }

}