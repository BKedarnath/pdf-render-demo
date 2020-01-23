package com.example.pdfrenderdemo.controllers;


import com.example.pdfrenderdemo.model.PDFContent;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PDFController {

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/getPDF")
    public byte[] welcomeController() throws IOException {
     //   Path path = Paths.get("/Users/bkedarn/Documents/dummy.pdf");
        Resource resource = resourceLoader.getResource("classpath:KCG_HUP_1.pdf");

        return Files.readAllBytes(Paths.get(resource.getURI()));

    }

    @PostMapping("/putPDF")
    public String updatePDF(@RequestBody PDFContent pdfContent) throws IOException {
        try
        {
            System.out.println("Came here");
            System.out.println(pdfContent.getUpdatedPDF());
            //This will decode the String which is encoded by using Base64 class
            //byte[] pdfBytes= Base64.decodeBase64(updatedPDF.substring(updatedPDF.indexOf(",")));
            byte[] pdfBytes= Base64.decodeBase64(pdfContent.getUpdatedPDF());

            String directory= "/Users/bkedarn/Documents/updated.pdf";

            new FileOutputStream(directory).write(pdfBytes);
            return "Success";
        }
        catch(Exception e)
        {
            return "error = "+e;
        }
    }

}
