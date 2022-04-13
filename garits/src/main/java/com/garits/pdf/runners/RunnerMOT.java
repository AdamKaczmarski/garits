package com.garits.pdf.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.garits.report.PdfFileServiceMOT;
import org.springframework.stereotype.Component;


public class RunnerMOT implements ApplicationRunner {

    private PdfFileServiceMOT pdfFileServiceMOT;

    @Autowired

    public RunnerMOT(PdfFileServiceMOT pdfFileServiceMOT) {

        this.pdfFileServiceMOT = pdfFileServiceMOT;

    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        pdfFileServiceMOT.pdfCreationMOT();
        System.out.println("MOT PDF File Created");


    }

}
