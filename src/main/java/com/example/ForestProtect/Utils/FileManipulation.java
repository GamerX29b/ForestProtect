package com.example.ForestProtect.Utils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import com.example.ForestProtect.Base.Photos;
import com.example.ForestProtect.Base.PhotosRepository;
import com.example.ForestProtect.controllers.HelloController;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Random;

import static java.lang.Double.valueOf;

@Component
public class FileManipulation {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    PhotosRepository photosRepository;

    @Value("${file.folder}")
    private String fileAdress;


    private long user_id = 100;

    public String savePhoto(String xcoord, String ycoord, MultipartFile file, String date){

        Random random = new Random();
        String name = String.valueOf(random.nextInt());
        String subdata;
        double latitude = valueOf(xcoord);
        double longitude = valueOf(ycoord);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
                //место для удара головой в FTP хранилище
                File output = new File(fileAdress+ name + ".jpg");
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

                    }
                }
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(formatter.parse(date));


                Photos photos = new Photos();
                photos.setId_user(user_id);
                photos.setVerification(false);
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
        return subdata;
    }
    public void deletePhoto(String filename){
            File file = new File(fileAdress + filename + ".jpg");

            boolean deleted = file.delete();

            if (deleted) {
                LOGGER.error("Не удалось удалить");
            }
    }
}
