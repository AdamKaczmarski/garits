package com.garits.pdf.runners;

import com.garits.report.PdfFileServiceJobSheet;
import com.garits.report.PdfFileServiceLedger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


public class RunnerJobSheet implements ApplicationRunner {

    private PdfFileServiceJobSheet pdfFileServiceJobSheet;

    @Autowired

    public RunnerJobSheet(PdfFileServiceJobSheet pdfFileServiceJobSheet) {

        this.pdfFileServiceJobSheet = pdfFileServiceJobSheet;

    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        pdfFileServiceJobSheet.pdfCreationJobSheet();
        System.out.println("Job Sheet PDF File Created");


    }

}
