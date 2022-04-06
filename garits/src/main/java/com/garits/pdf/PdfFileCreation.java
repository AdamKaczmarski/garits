package com.garits.pdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.garits.report.PdfFileService;

@SpringBootApplication
public class PdfFileCreation {

    public static void main(String[] args) {
        SpringApplication.run(PdfFileCreation.class, args);

    }

}