package com.garits.pdf;

import com.garits.report.PdfFileServicePartsOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
    public class RunnerPartsOrder implements ApplicationRunner {

        private PdfFileServicePartsOrder pdfFileServicePartsOrder;

        @Autowired
        public RunnerPartsOrder(PdfFileServicePartsOrder pdfFileServicePartsOrder) {

            this.pdfFileServicePartsOrder = pdfFileServicePartsOrder;

        }
        @Override
        public void run(ApplicationArguments args) throws Exception {

            pdfFileServicePartsOrder.pdfCreationStock();
            System.out.println("Parts Order Pdf File Created");


        }

    }


