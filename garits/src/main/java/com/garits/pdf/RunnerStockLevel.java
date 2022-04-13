package com.garits.pdf;

import com.garits.report.PdfFileServiceStockLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class RunnerStockLevel implements ApplicationRunner {

    private PdfFileServiceStockLevel pdfFileServiceStockLevel;

    @Autowired
    public RunnerStockLevel(PdfFileServiceStockLevel pdfFileServiceStockLevel) {

        this.pdfFileServiceStockLevel = pdfFileServiceStockLevel;

    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        PdfFileServiceStockLevel.pdfCreation();
        System.out.println("Stock Level PDF File Created");


    }

}
