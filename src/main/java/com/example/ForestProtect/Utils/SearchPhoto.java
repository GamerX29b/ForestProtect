package com.example.ForestProtect.Utils;

import com.example.ForestProtect.Base.Photos;
import com.example.ForestProtect.Base.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class SearchPhoto {

    @Autowired
    PhotosRepository photosRepository;

    public List<Photos> getPhotoList (){
        List<Photos> list = new LinkedList<Photos>();

        photosRepository.findAll(new PageRequest(1, 9));


        return list;
    }
 }
