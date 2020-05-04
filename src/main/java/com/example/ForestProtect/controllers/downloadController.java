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
    //контроллеры шрифтов Eot
    @RequestMapping(value = "/Font/StixFont.eot",
            method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> downloadEot(String param) throws IOException {

        Path patc = Paths.get("src/main/resources/static/Font/StixFont.ttf");
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(patc));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    @RequestMapping(value = "/Font/StixFont.otf",
            method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> downloadOtf(String param) throws IOException {

        Path patc = Paths.get("src/main/resources/static/Font/StixFont.otf");
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(patc));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    @RequestMapping(value = "/Font/StixFont.svg",
            method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> downloadSvg(String param) throws IOException {

        Path patc = Paths.get("src/main/resources/static/Font/StixFont.svg");
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(patc));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    @RequestMapping(value = "/Font/StixFont.ttf",
            method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> downloadTtf(String param) throws IOException {

        Path patc = Paths.get("src/main/resources/static/Font/StixFont.ttf");
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(patc));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    @RequestMapping(value = "/Font/StixFont.woff",
            method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> downloadWoff(String param) throws IOException {

        Path patc = Paths.get("src/main/resources/static/Font/StixFont.woff");
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(patc));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    @RequestMapping(value = "/Font/StixFont.woff2",
            method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> downloadWoff2(String param) throws IOException {

        Path patc = Paths.get("src/main/resources/static/Font/StixFont.woff2");
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(patc));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
