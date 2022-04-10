package com.garits.pdf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.garits.report.PdfFileServiceInvoice;


@Component
public class RunnerInvoice implements ApplicationRunner {

    private PdfFileServiceInvoice pdfFileServiceInvoice;

    @Autowired
    public RunnerInvoice(PdfFileServiceInvoice pdfFileServiceInvoice) {

        this.pdfFileServiceInvoice = pdfFileServiceInvoice;

    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        pdfFileServiceInvoice.pdfCreation();
        System.out.println("Invoice PDF File Created");


    }

}