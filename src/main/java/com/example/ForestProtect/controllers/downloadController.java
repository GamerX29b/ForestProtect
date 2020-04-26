package com.example.ForestProtect.controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class downloadController {

    @RequestMapping(value = "/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ByteArrayResource> photos (@RequestParam("name") String name) throws InterruptedException, IOException {


            Path patc = Paths.get("photo/" + name + ".jpg");
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(patc));
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
    }

}
