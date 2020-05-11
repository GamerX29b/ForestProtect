package com.example.ForestProtect.Utils;

import com.example.ForestProtect.Base.Documents;
import com.example.ForestProtect.Base.DocumentsRepository;
import com.example.ForestProtect.Base.Photos;
import com.example.ForestProtect.Base.PhotosRepository;
import com.example.ForestProtect.Classes.PagePhotoResult;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class SearchPhoto {

    @Autowired
    DocumentsRepository documentsRepository;

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

    public List<PagePhotoResult> getSearchResult (String id, String name, boolean violation, String startDateString, String endDateString, String coordinates, int page)
    {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "date"));

        Photos filterPhotos = new Photos(); //экземпляр для фильтрации
        filterPhotos.setVerification(true); //В этом списке отображаются только верифицированные
        Page<Photos> pages = Page.empty();

        if(id != null && !(id.trim().length() == 0)) {  //Если у нас указан ID мы игнорируем всё кроме него
            filterPhotos.setId(Long.valueOf(id));
            ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("File")
                    .withIgnorePaths("name")
                    .withIgnorePaths("id_user")
                    .withIgnorePaths("date")
                    .withIgnorePaths("coordinates")
                    .withIgnorePaths("verification");
            Example<Photos> example = Example.of(filterPhotos, matcher);
            pages = photosRepository.findAll(example, pageable);
        } else if(name != null && !(name.trim().length() == 0)) {  //Если у нас указан ID мы игнорируем всё кроме него
            filterPhotos.setName(name);
            ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("File")
                    .withIgnorePaths("id")
                    .withIgnorePaths("id_user")
                    .withIgnorePaths("date")
                    .withIgnorePaths("coordinates")
                    .withIgnorePaths("verification");
            Example<Photos> example = Example.of(filterPhotos, matcher);
            pages = photosRepository.findAll(example, pageable);
        } else if(startDateString != null &&
                endDateString != null &&
                !(startDateString.trim().length() == 0) &&
                !(endDateString.trim().length() == 0)){ //Если у нас указан диапазон дат мы ищем только в нём
            Date startDate = parseDate(startDateString);
            Date endDate = parseDate(endDateString);
            pages = photosRepository.findAllByDateBetween(startDate, endDate, pageable);
        } else if (startDateString != null &&
                endDateString != null &&
                !(startDateString.trim().length() == 0) &&
                (endDateString.trim().length() == 0)){//Если указана дата начала поиска
            pages = photosRepository.findAllWithDateBefore(parseDate(startDateString), pageable);
        } else if (startDateString != null &&
                endDateString != null &&
                (startDateString.trim().length() == 0) && !
                (endDateString.trim().length() == 0)) {//Если указана дата конца поиска
            pages = photosRepository.findAllDateAfter(parseDate(endDateString), pageable);
        } else if (coordinates != null &&
                !(coordinates.trim().length() == 0)){ //если указаны координаты

            pages = photosRepository.findByCoorinat(coordinates, pageable);
        } else {

            ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id")
                    .withIgnorePaths("File")
                    .withIgnorePaths("name")
                    .withIgnorePaths("id_user")
                    .withIgnorePaths("date")
                    .withIgnorePaths("coordinates");
            Example<Photos> example = Example.of(filterPhotos, matcher);
           pages = photosRepository.findAll(example, pageable);
        }

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
        return pagePhotoResultList;
    }

    private Date parseDate(String date){
        try {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();

        calendar.setTime(formatter.parse(date));

        return calendar.getTime();
        } catch (ParseException e) {
            System.out.println("Чемодан говна " + e);
            return new Date();
        }

    }
 }
