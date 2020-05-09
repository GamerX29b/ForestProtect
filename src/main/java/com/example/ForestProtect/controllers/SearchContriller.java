package com.example.ForestProtect.controllers;

import com.example.ForestProtect.Base.Documents;
import com.example.ForestProtect.Base.DocumentsRepository;
import com.example.ForestProtect.Base.Photos;
import com.example.ForestProtect.Base.PhotosRepository;
import com.example.ForestProtect.Classes.PagePhotoResult;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class SearchContriller {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    DocumentsRepository documentsRepository;

    @Autowired
    PhotosRepository photosRepository;

    private long user_id = 100;
    @RequestMapping(value="/search.html", method= RequestMethod.GET)
    public @ResponseBody
    ModelAndView handleFileUpload(
            @RequestParam(required = false ) String id,
            @RequestParam(required = false ) boolean violation,
            @RequestParam(required = false ) String startDate,
            @RequestParam(required = false ) String endDate,
            @RequestParam(required = false ) String coordinates,
            @RequestParam(required = false, defaultValue = "0") int page){


        Pageable pageable = PageRequest.of(page, 10);
        pageable.getSortOr(Sort.by("Data"));

        Photos filterPhotos = new Photos();
        if(id != null && !(id.trim().length() == 0)) {
            filterPhotos.setId(Long.valueOf(id));
        }
        filterPhotos.setVerification(true);

        ExampleMatcher matcher = ExampleMatcher.matching();


        //Example<Photos> example = Example.of(filterPhotos);

        Page<Photos> pages = photosRepository.findAll(pageable);
        List<Photos> photos = pages.getContent();
        List<PagePhotoResult> pagePhotoResultList = new ArrayList<>();

        if(!photos.isEmpty()){
            for (Photos photo: photos) {
                PagePhotoResult pagePhotoResult = new PagePhotoResult();
                ExampleMatcher matcherSecond = ExampleMatcher.matching()
                        .withIgnorePaths("id")
                        .withIgnorePaths("id_user")
                        .withIgnorePaths("date")
                        .withIgnorePaths("violation")
                        .withIgnorePaths("content");

                Documents documentSearchExample = new Documents();
                documentSearchExample.setId_photo(photo.getId());
                Example<Documents> exampleSecond = Example.of(documentSearchExample, matcherSecond);

                Optional<Documents> documentsOptional = documentsRepository.findOne(exampleSecond);
                pagePhotoResult.setCoordinates(photo.getCoordinates());
                pagePhotoResult.setDate(photo.getDate());
                pagePhotoResult.setId(photo.getId());
                pagePhotoResult.setName(photo.getName());
                if(documentsOptional.isPresent()) {
                    Documents documents = documentsOptional.get();
                    pagePhotoResult.setViolation(documents.getViolation());
                    pagePhotoResult.setDocument(documents.getContent());
                }
                pagePhotoResultList.add(pagePhotoResult);
            }


        }

        ModelAndView mv = new ModelAndView("search");
        mv.addObject("photo", pagePhotoResultList);
        return mv;
    }
}
