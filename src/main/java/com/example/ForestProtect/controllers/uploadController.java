package com.example.ForestProtect.controllers;

import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import com.example.ForestProtect.Base.Photos;
import com.example.ForestProtect.Base.PhotosRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.imageio.metadata.IIOMetadata;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.*;

import java.io.FileOutputStream;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.lang.Double.valueOf;

@Controller
public class uploadController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HelloController.class);


    @Autowired
    PhotosRepository photosRepository;





    private long user_id = 100;
    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody
    ModelAndView handleFileUpload(
                            @RequestParam("xcoord") String xcoord,
                            @RequestParam("ycoord") String ycoord,
                            @RequestParam("file") MultipartFile file,
                            @RequestParam("date") String date){

        Random random = new Random();
        String name = String.valueOf(random.nextInt());
        String subdata;
        double latitude = valueOf(xcoord);
        double longitude = valueOf(ycoord);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
                File output = new File("photo\\"+ name + ".jpg");
                ImageIO.write(image, "jpg", output);

                        Metadata metadata = ImageMetadataReader.readMetadata(new ByteArrayInputStream(bytes));
                        if(!metadata.hasErrors()){

                            Collection<GpsDirectory> collection = metadata.getDirectoriesOfType(GpsDirectory.class);

                            if (!collection.isEmpty()) {
                                GeoLocation geoLocation = null;
                                for (GpsDirectory gpsDirectory : collection) {
                                    geoLocation = gpsDirectory.getGeoLocation();
                                }
                                latitude = geoLocation.getLatitude();
                                longitude = geoLocation.getLongitude();

                            System.out.println(latitude + " " + longitude);
                            }
                        }
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(formatter.parse(date));

                System.out.println(date);
                Photos photos = new Photos();
                photos.setId_user(user_id);
                photos.setName(name);
                String coordinates = latitude + " " + longitude;
                photos.setCoordinates(coordinates);
                photos.setDate(calendar.getTime());

                photosRepository.save(photos);


                subdata = "Вы удачно загрузили фото, оно получило имя " + name + " ";
            } catch (IllegalArgumentException e) {
                subdata = "Это ещё что? Обмануть меня удумал?! Загрузть можно только фото!";
            } catch (Exception e) {
                subdata = "Ошибка сервера " + e;
            }
        } else {
            subdata = "Файл не прикреплён";
        }
        ModelAndView mv = new ModelAndView("download");
        mv.addObject("subdata", subdata);
        return mv;
    }
}
