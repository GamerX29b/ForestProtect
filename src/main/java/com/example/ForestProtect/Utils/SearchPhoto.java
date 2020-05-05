package com.example.ForestProtect.Utils;

import com.example.ForestProtect.Base.Photos;
import com.example.ForestProtect.Base.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;


import java.util.List;


@Component
public class SearchPhoto {

    @Autowired
    PhotosRepository photosRepository;

    @Autowired
    FileManipulation fileManipulation;

    public List<Photos> getPhotoListStarted (){
        List<Photos> list =  photosRepository.get4Photo();
        return list;
    }
    public void setNotVerify  (Photos notVerify){
        fileManipulation.deletePhoto(notVerify.getName());
        photosRepository.delete(notVerify);
    }

    public void setVerify  (Photos notVerify){
        notVerify.setVerification(true);
        photosRepository.save(notVerify);
    }

    public Photos getPhotoForVerification (){
        Photos photos =  photosRepository.getNotVerifyPhoto();
        return photos;
    }
 }
