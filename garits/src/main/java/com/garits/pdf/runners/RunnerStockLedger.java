package com.garits.pdf.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.garits.report.PdfFileServiceLedger;


public class RunnerStockLedger implements ApplicationRunner {

    private PdfFileServiceLedger pdfFileServiceLedger;

    @Autowired

    public RunnerStockLedger(PdfFileServiceLedger pdfFileServiceLedger) {

        this.pdfFileServiceLedger = pdfFileServiceLedger;

    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        pdfFileServiceLedger.pdfCreationLedger();
        System.out.println("Stock Ledger PDF File Created");


    }

}
