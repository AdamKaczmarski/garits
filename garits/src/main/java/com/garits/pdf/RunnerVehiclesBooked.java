package com.garits.pdf;

import com.garits.report.PdfFileServiceVehiclesBooked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class RunnerVehiclesBooked implements ApplicationRunner {

    private PdfFileServiceVehiclesBooked pdfFileServiceVehiclesBooked;

    @Autowired
    public RunnerVehiclesBooked(PdfFileServiceVehiclesBooked pdfFileServiceVehiclesBooked) {

        this.pdfFileServiceVehiclesBooked = pdfFileServiceVehiclesBooked;

    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        pdfFileServiceVehiclesBooked.pdfCreationVehiclesBooked();
        System.out.println("Vehicles Booked PDF File Created");


    }

}
