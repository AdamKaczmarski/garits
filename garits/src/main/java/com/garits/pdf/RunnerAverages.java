package com.garits.pdf;

import com.garits.report.PdfFileServiceAverages;
import com.garits.report.PdfFileServiceLedger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RunnerAverages implements ApplicationRunner {

    private PdfFileServiceAverages pdfFileServiceAverages;

    @Autowired

    public RunnerAverages(PdfFileServiceAverages pdfFileServiceAverages) {

        this.pdfFileServiceAverages = pdfFileServiceAverages;

    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        pdfFileServiceAverages.pdfCreationAverages();
        System.out.println("Averages PDF File Created");


    }

}
