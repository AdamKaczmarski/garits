package com.garits.vehicle;

import com.garits.customer.CustomerRepository;
import com.garits.pdf.GeneratePdfReport;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MotReminder {

    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    CustomerRepository customerRepository;
    //@Scheduled(fixedRate = 10000)
    @Scheduled(fixedRate = 86400000) // 24hours
    public void sendReminder() {
        Iterable<Vehicle> vehicles=vehicleRepository.findVehicleCloseToMot();
        for (Vehicle vehicle:vehicles) {
            vehicle.setCustomer(customerRepository.findCustomerForVehicle(vehicle.getIdRegNo()));
            System.out.println("Sending MOT reminder to "+vehicle.getCustomer().iterator().next().getEmail());
            GeneratePdfReport.motReminder(vehicle);
        }
    }
}
