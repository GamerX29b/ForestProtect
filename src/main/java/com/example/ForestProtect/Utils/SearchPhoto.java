package com.example.ForestProtect.Utils;

import com.example.ForestProtect.Base.Photos;
import com.example.ForestProtect.Base.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
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

        Pageable pageable = PageRequest.of(1,9);
        Page<Photos> page = photosRepository.findAll(pageable);
        List<Photos> list = page.get().collect(Collectors.toList());

        return list;
    }
 }
