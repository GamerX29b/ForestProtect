package com.example.ForestProtect.Utils;

import com.example.ForestProtect.Base.Photos;
import com.example.ForestProtect.Base.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SearchPhoto {

    @Autowired
    PhotosRepository photosRepository;

    public List<Photos> getPhotoListStarted (){
        List<Photos> list =  photosRepository.get4Photo();
        return list;
    }
    public void setNotVerify  (Photos notVerify){
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
