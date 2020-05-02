package com.example.ForestProtect.controllers;

import com.example.ForestProtect.Base.Documents;
import com.example.ForestProtect.Base.DocumentsRepository;
import com.example.ForestProtect.Base.Photos;
import com.example.ForestProtect.Base.PhotosRepository;
import com.example.ForestProtect.Utils.SearchPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class VerificationController {
    private final SearchPhoto searchPhoto;

    @Autowired
    public VerificationController(SearchPhoto searchPhoto){
        this.searchPhoto = searchPhoto;
    }

    @Autowired
    PhotosRepository photosRepository;

    @Autowired
    DocumentsRepository documentsRepository;


    @RequestMapping(value = "/ImageVerification.html", method = RequestMethod.GET)
    private ModelAndView verification(@RequestParam(required = false) String iDphotos,
                                      @RequestParam(required = false) boolean yes,
                                      @RequestParam(required = false) boolean violation,
                                      @RequestParam(required = false) String content){
        ModelAndView mv = new ModelAndView("ImageVerification");



        if (iDphotos != null){
            Optional<Photos> photos = photosRepository.findById(Long.valueOf(iDphotos));
            Photos photosBack = photos.get();
            if (yes){
                searchPhoto.setVerify(photosBack);
                if (violation){
                    Documents documents = new Documents();
                    documents.setContent(content);
                    documents.setDate(photosBack.getDate());
                    documents.setId_photo(photosBack.getId());
                    documents.setId_user(photosBack.getId_user());
                    documents.setViolation(violation);

                   // documentsRepository.save(documents);

                }
            }else {
               // searchPhoto.setNotVerify(photosBack);
            }
        }

        String infos = "";

        Photos photo = searchPhoto.getPhotoForVerification();
        if (photo == null){
            infos = "не верифицированных фото больше нет";
        }
        mv.addObject("Info", infos);
        mv.addObject("photo", photo);
        return mv;
    }
}
