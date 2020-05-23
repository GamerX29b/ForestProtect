package com.example.ForestProtect.controllers;

import com.example.ForestProtect.Base.Photos;
import com.example.ForestProtect.Base.PhotosRepository;
import com.example.ForestProtect.Utils.SearchPhoto;
import com.sun.javafx.util.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@Controller
public class HelloController {

    @Value("${compile.version}")
    private String thisVersion;

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    private final SearchPhoto searchPhoto;

    @Autowired
    public HelloController(SearchPhoto searchPhoto){
        this.searchPhoto = searchPhoto;
    }

    @Autowired
    PhotosRepository photosRepository;

    @RequestMapping(value = "/",  produces={"text/html; charset=UTF-8"})
    private ModelAndView hello () throws InterruptedException {
        ModelAndView mv = new ModelAndView("hello");

        mv.addObject("Version", thisVersion);
        mv.addObject("AllPhoto", searchPhoto.photoAll());
        mv.addObject("AllViolation",searchPhoto.violationAll());
        return mv;
    }
    @RequestMapping(value = "/view.html",  produces={"text/html; charset=UTF-8"})
    private ModelAndView view () throws InterruptedException {
        ModelAndView mv = new ModelAndView("view");
        List<Photos> photo = searchPhoto.getPhotoListStarted();
        mv.addObject("photolist", photo);
        return mv;
    }

    @RequestMapping(value = "/download.html")
    private ModelAndView download(){
        ModelAndView mv = new ModelAndView("download");

        return mv;
    }

}