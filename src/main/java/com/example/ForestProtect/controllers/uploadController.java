package com.example.ForestProtect.controllers;


import com.example.ForestProtect.Base.PhotosRepository;
import com.example.ForestProtect.Utils.FileManipulation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class uploadController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HelloController.class);


    @Autowired
    PhotosRepository photosRepository;

    @Autowired
    FileManipulation fileManipulation;

    private long user_id = 100;
    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody
    ModelAndView handleFileUpload(
                            @RequestParam("xcoord") String xcoord,
                            @RequestParam("ycoord") String ycoord,
                            @RequestParam("file") MultipartFile file,
                            @RequestParam("date") String date){

        String subdata = fileManipulation.savePhoto(xcoord, ycoord, file, date);

        ModelAndView mv = new ModelAndView("download");
        mv.addObject("subdata", subdata);
        return mv;
    }
}
