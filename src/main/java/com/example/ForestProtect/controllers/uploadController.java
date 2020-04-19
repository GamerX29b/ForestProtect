package com.example.ForestProtect.controllers;

import com.example.ForestProtect.Base.Photos;
import com.example.ForestProtect.Base.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class uploadController {



    @Autowired
    PhotosRepository photosRepository;





    private long user_id = 100;
    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(
                            @RequestParam("xcoord") String xcoord,
                            @RequestParam("ycoord") String ycoord,
                            @RequestParam("file") MultipartFile file,
                            @RequestParam("date") String date){

        Random random = new Random();
        String name = String.valueOf(random.nextInt());

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + ".jpg")));
                stream.write(bytes);
                stream.close();


                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(formatter.parse(date));

                System.out.println(date);
                Photos photos = new Photos();
                photos.setId_user(user_id);
                photos.setName(name);
                String coordinates = xcoord + " " + ycoord;
                photos.setCoordinates(coordinates);
                photos.setDate(calendar.getTime());




                //photosRepository.save(photos);


                return "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }
}
