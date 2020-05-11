package com.example.ForestProtect.controllers;


import com.example.ForestProtect.Classes.PagePhotoResult;

import com.example.ForestProtect.Classes.SearchPaket;
import com.example.ForestProtect.Utils.SearchPhoto;
import org.hibernate.criterion.MatchMode;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


@Controller
public class SearchContriller {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    SearchPhoto searchPhoto;

    private long user_id = 100;
    @RequestMapping(value="/search.html", method= RequestMethod.GET)
    public @ResponseBody
    ModelAndView handleFileUpload(
            @RequestParam(required = false ) String id,
            @RequestParam(required = false ) String name,
            @RequestParam(required = false ) boolean violation,
            @RequestParam(required = false ) String startDate,
            @RequestParam(required = false ) String endDate,
            @RequestParam(required = false ) String coordinates,
            @RequestParam(required = false, defaultValue = "0") int page){


        List<PagePhotoResult> pagePhotoResultList = searchPhoto.getSearchResult(id, name, violation, startDate, endDate, coordinates,  page);
        SearchPaket searchPaket = new SearchPaket();
        searchPaket.setId(id);
        searchPaket.setName(name);
        searchPaket.setViolation(violation);
        searchPaket.setStartDate(startDate);
        searchPaket.setEndDate(endDate);
        searchPaket.setPage(page);



        ModelAndView mv = new ModelAndView("search");
        mv.addObject("photo", pagePhotoResultList);
        mv.addObject("searchPaket", searchPaket);
        return mv;
    }
}
