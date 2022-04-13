package com.garits.pdf;

import com.garits.customer.CustomerRepository;
import com.garits.job.Job;
import com.garits.job.JobRepository;
import com.garits.order.Order;
import com.garits.order.OrderRepository;
import com.garits.order.PartsOrdersDetail;
import com.garits.order.PartsOrdersDetailRepository;
import com.garits.part.Part;
import com.garits.part.PartRepository;
import com.garits.payment.job.PaymentJob;
import com.garits.payment.job.PaymentJobRepository;
import com.garits.user.User;
import com.garits.vehicle.Vehicle;
import com.garits.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RequestMapping("/pdf")
@RestController
public class PdfGeneratorController {
    @Autowired
    JobRepository jobRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    PaymentJobRepository paymentJobRepository;
    @Autowired
    PartRepository partRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PartsOrdersDetailRepository partsOrdersDetailRepository;
    @GetMapping(value = "/stock-ledger", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<InputStreamResource> generateStockLedger() {
        Iterable<Part> parts = partRepository.findAll();
        ByteArrayInputStream bis = GeneratePdfReport.stockLedger(parts);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Stock_ledger_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/mot-reminder/{idVehicle}", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<InputStreamResource> generateMotReminder(@PathVariable Integer idVehicle) {
        Vehicle v = vehicleRepository.findById(idVehicle).orElseThrow(() -> new RuntimeException("Vehicle not found"));
        v.setCustomer(customerRepository.findCustomerForVehicle(v.getIdRegNo()));
        ByteArrayInputStream bis = GeneratePdfReport.motReminder(v);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=GARITS_Mot_Reminder.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/job-sheet/{idJob}", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<InputStreamResource> generateJobSheet(@PathVariable("idJob") Integer idJob) {
        Job j = jobRepository.findById(idJob).orElseThrow(() -> new RuntimeException("Job not found"));
        j.getVehicle().setCustomer(customerRepository.findCustomerForVehicle(j.getVehicle().getIdRegNo()));
        if (!j.getStatus().equals("booked")) {
            User u = new User(j.getUser().iterator().next().getIdUser(), j.getUser().iterator().next().getFirstName(), j.getUser().iterator().next().getLastName());
            j.getUser().clear();
            j.getUser().add(u);
        }
        if (j.getParts() != null) {
            for (Part p : j.getParts()) {
                Integer q = jobRepository.getQuantityOfPart(p.getIdPart(), j.getIdJob());
                if (q != null) {
                    p.setQuantityUsed(q);
                }
            }
        }
        ByteArrayInputStream bis = GeneratePdfReport.jobSheet(j);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Job_sheet.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/invoice/{idJob}", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<InputStreamResource> generateInvoice(@PathVariable Integer idJob) {
        Job j = jobRepository.findById(idJob).orElseThrow(() -> new RuntimeException("Job not found"));
        j.getVehicle().setCustomer(customerRepository.findCustomerForVehicle(j.getVehicle().getIdRegNo()));
       /* if (!j.getStatus().equals("booked")) {
            System.out.println("ADDING USER");
            User u = new User(j.getUser().iterator().next().getIdUser(), j.getUser().iterator().next().getFirstName(), j.getUser().iterator().next().getLastName());
            j.getUser().clear();
            j.getUser().add(u);
        }*/
        if (j.getParts() != null) {
            for (Part p : j.getParts()) {
                Integer q = jobRepository.getQuantityOfPart(p.getIdPart(), j.getIdJob());
                if (q != null) {
                    p.setQuantityUsed(q);
                }
            }
        }
        PaymentJob p = paymentJobRepository.findPaymentByJobId(j.getIdJob());
        ByteArrayInputStream bis = GeneratePdfReport.invoice(j, p);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=GARITS_Invoice.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/parts-order/{idOrder}", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<InputStreamResource> generatePartsOrder(@PathVariable Integer idOrder) {
        Order o  = orderRepository.findById(idOrder).orElseThrow(() -> new RuntimeException("Order not found"));
        Iterable<PartsOrdersDetail> pod = partsOrdersDetailRepository.getAllByOrderId(idOrder);
        ByteArrayInputStream bis = GeneratePdfReport.partsOrder(o,pod);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Parts_order.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/averages", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<InputStreamResource> generateAveragesReport() {
        ByteArrayInputStream bis = GeneratePdfReport.averagesReport();
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Average_reports.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/booking-stats", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<InputStreamResource> generateBookingStats() {
        ByteArrayInputStream bis = GeneratePdfReport.bookingStats();
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Booking_stats.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


}
