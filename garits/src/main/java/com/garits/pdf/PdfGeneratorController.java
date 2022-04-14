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
/*import com.garits.report.StatReport;
import com.garits.report.StatReports;*/
import com.garits.pdf.payloads.StockLevel;
import com.garits.report.StatReport;
import com.garits.user.User;
import com.garits.vehicle.Vehicle;
import com.garits.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("java-jpa-scheduled-day");
    private final EntityManager em = emFactory.createEntityManager();

/*    @Autowired
    StatReports statReports;*/


    @GetMapping(value = "/stock-ledger", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    ResponseEntity<InputStreamResource> generateStockLedger() {
        Iterable<Part> parts = partRepository.findAll();
        ByteArrayInputStream bis = GeneratePdfReport.stockLedger(parts);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Stock_ledger_report.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/mot-reminder/{idVehicle}", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    ResponseEntity<InputStreamResource> generateMotReminder(@PathVariable Integer idVehicle) {
        Vehicle v = vehicleRepository.findById(idVehicle).orElseThrow(() -> new RuntimeException("Vehicle not found"));
        v.setCustomer(customerRepository.findCustomerForVehicle(v.getIdRegNo()));
        ByteArrayInputStream bis = GeneratePdfReport.motReminder(v);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=GARITS_Mot_Reminder.pdf");
        System.out.println("SENDING MOT REMINDER TO " + v.getCustomer().iterator().next().getEmail());
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/job-sheet/{idJob}", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
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

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/invoice/{idJob}", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
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

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/parts-order/{idOrder}", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    ResponseEntity<InputStreamResource> generatePartsOrder(@PathVariable Integer idOrder) {
        Order o = orderRepository.findById(idOrder).orElseThrow(() -> new RuntimeException("Order not found"));
        Iterable<PartsOrdersDetail> pod = partsOrdersDetailRepository.getAllByOrderId(idOrder);
        ByteArrayInputStream bis = GeneratePdfReport.partsOrder(o, pod);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Parts_order.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/averages/{idUser}", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasRole('FRANCHISEE')")
    ResponseEntity<InputStreamResource> generateAveragesReport(@PathVariable(required = false)Integer idUser) {
        Query q1 = em.createNativeQuery("SELECT ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'\n" + "FROM jobs j \n" + "INNER JOIN jobs_payments jp ON jp.job_id=j.id_job\n" + "INNER JOIN payments p on p.id_payment = jp.payment_id ;");
        Query q2 = em.createNativeQuery("SELECT s.service_name AS 'Job type',ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'\n" + "FROM jobs j \n" + "INNER JOIN jobs_payments jp ON jp.job_id=j.id_job\n" + "INNER JOIN payments p on p.id_payment = jp.payment_id\n" + "INNER JOIN jobs_services js ON j.id_job = js.job_id\n" + "INNER JOIN services s ON s.id_service=js.service_id\n" + "GROUP BY s.service_name;");
        Query q3 = null;
        if (idUser != null && idUser != 0) {
            System.out.println("USER ID: " + idUser);
            q3 = em.createNativeQuery("SELECT s.service_name AS 'Job type',CONCAT(u.first_name,' ',u.last_name) AS 'Mechanic',ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'\n" + "FROM jobs j \n" + "INNER JOIN jobs_payments jp ON jp.job_id=j.id_job\n" + "INNER JOIN payments p on p.id_payment = jp.payment_id\n" + "INNER JOIN jobs_services js ON j.id_job = js.job_id\n" + "INNER JOIN services s ON s.id_service=js.service_id\n" + "INNER JOIN users_jobs uj ON uj.job_id = j.id_job\n" + "INNER JOIN users u ON u.id_user = uj.user_id\n" + "WHERE id_user=" + idUser + "\n" + "GROUP BY s.service_name, CONCAT(u.first_name,' ',u.last_name)\n" + "ORDER BY 2,1;");
        } else {
            System.out.println("NO USER");
            q3 = em.createNativeQuery("SELECT s.service_name AS 'Job type',CONCAT(u.first_name,' ',u.last_name) AS 'Mechanic',ROUND(AVG(j.act_time_min),2) AS 'Average job time', ROUND(AVG(p.amount),2) AS 'Average price'\n" + "FROM jobs j \n" + "INNER JOIN jobs_payments jp ON jp.job_id=j.id_job\n" + "INNER JOIN payments p on p.id_payment = jp.payment_id\n" + "INNER JOIN jobs_services js ON j.id_job = js.job_id\n" + "INNER JOIN services s ON s.id_service=js.service_id\n" + "INNER JOIN users_jobs uj ON uj.job_id = j.id_job\n" + "INNER JOIN users u ON u.id_user = uj.user_id\n" + "GROUP BY s.service_name, CONCAT(u.first_name,' ',u.last_name)\n" + "ORDER BY 2,1;");
        }
        List<Object[]> r1 = q1.getResultList();
        List<Object[]> r2 = q2.getResultList();
        List<Object[]> r3 = q3.getResultList();

        ByteArrayInputStream bis = GeneratePdfReport.averagesReport(r1, r2, r3);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Average_reports.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/booking-stats", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasRole('FRANCHISEE')")
    ResponseEntity<InputStreamResource> generateBookingStats() {
        Query queryPerMonthPerYear = em.createNativeQuery("SELECT CONCAT(YEAR(j.create_date),'-',MONTH(j.create_date)),count(j.id_job)\n" + "FROM jobs j \n" + "GROUP BY MONTH(j.create_date) ORDER BY 1,2");
        Query queryOverall = em.createNativeQuery("SELECT CAST(COUNT(id_job) AS CHAR(10)),'dummy' from jobs");
        Query queryPerJob = em.createNativeQuery("SELECT s.service_name,COUNT(j.id_job)\n" + "FROM jobs j \n" + "INNER JOIN jobs_services js ON j.id_job = js.job_id\n" + "INNER JOIN services s ON s.id_service=js.service_id\n" + "GROUP BY s.service_name");
        List<Object[]> resultsPerMonthPerYear = queryPerMonthPerYear.getResultList();
        List<Object[]> resultsOverall = queryOverall.getResultList();
        List<Object[]> resultsPerJob = queryPerJob.getResultList();
        ByteArrayInputStream bis = GeneratePdfReport.bookingStats(resultsPerMonthPerYear, resultsOverall, resultsPerJob);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Booking_stats.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/stock-level/{dateFrom}/{dateTo}", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    ResponseEntity<InputStreamResource> generateStockLevel(@PathVariable String dateFrom, @PathVariable String dateTo) {
        //List<StatReport> s = Collections.checkedList(em.createNamedQuery("Stats",StatReport.class).getResultList(),StatReport.class);
       /* Query query = em.createNativeQuery("SELECT\n" + "    p.part_name AS 'Part Name',\n" + "    p.code AS 'Part Code',\n" + "    p.manufacturer AS 'Manufacturer',\n" + "    p.vehicle_type AS 'Vehicle Type',\n" + "    p.year_s AS 'Year(s)',\n" + "    ROUND(p.price, 2) AS 'Price',\n" + "    (p.stock_level-IFNULL(po.quantity_ordered,0)+IFNULL(jp.quantity_used,0)) AS 'Initial Stock level',\n" + "    ROUND(p.price * p.stock_level, 2) AS 'Initial cost, £',\n" + "    IFNULL(jp.quantity_used,0) AS 'Used',\n" + "    IFNULL(po.quantity_ordered,0) AS 'Delivery',\n" + "    (\n" + "        IFNULL(p.stock_level,0) + IFNULL(po.quantity_ordered,0) - IFNULL(jp.quantity_used,0)\n" + "    ) AS 'New Stock level',\n" + "    ROUND(\n" + "        (\n" + "            IFNULL(p.stock_level,0) + IFNULL(po.quantity_ordered,0) - IFNULL(jp.quantity_used,0)\n" + "        ) * p.price,\n" + "        2\n" + "    ) AS 'Stock cost,£',\n" + "    p.stock_level_threshold AS 'Low level Threshold'\n" + "FROM\n" + "    parts p\n" + "    LEFT JOIN parts_orders po ON po.part_id = p.id_part\n" + "    LEFT JOIN orders o ON o.id_order = po.order_id\n" + "    LEFT JOIN jobs_parts jp ON p.id_part = jp.part_id\n" + "    LEFT JOIN jobs j ON j.id_job = jp.job_id\n" + "WHERE\n" + "    (o.status='completed'\n" + "    AND DATE(o.order_date) >= '" + dates.getDateFrom() + "' AND DATE(o.order_arrival) <= '" + dates.getDateTo() + "')\n" + "    OR (j.status='completed' AND DATE(j.create_date)>= '" + dates.getDateFrom() + "' AND DATE(j.fix_date) <= '" + dates.getDateTo() + "');");*/
        Query query = em.createNativeQuery("SELECT\n" + "    p.part_name AS 'Part Name',\n" + "    p.code AS 'Part Code',\n" + "    p.manufacturer AS 'Manufacturer',\n" + "    p.vehicle_type AS 'Vehicle Type',\n" + "    p.year_s AS 'Year(s)',\n" + "    ROUND(p.price, 2) AS 'Price',\n" + "    (p.stock_level-IFNULL(po.quantity_ordered,0)+IFNULL(jp.quantity_used,0)) AS 'Initial Stock level',\n" + "    ROUND(p.price * p.stock_level, 2) AS 'Initial cost, £',\n" + "    IFNULL(jp.quantity_used,0) AS 'Used',\n" + "    IFNULL(po.quantity_ordered,0) AS 'Delivery',\n" + "    (\n" + "        IFNULL(p.stock_level,0) + IFNULL(po.quantity_ordered,0) - IFNULL(jp.quantity_used,0)\n" + "    ) AS 'New Stock level',\n" + "    ROUND(\n" + "        (\n" + "            IFNULL(p.stock_level,0) + IFNULL(po.quantity_ordered,0) - IFNULL(jp.quantity_used,0)\n" + "        ) * p.price,\n" + "        2\n" + "    ) AS 'Stock cost,£',\n" + "    p.stock_level_threshold AS 'Low level Threshold'\n" + "FROM\n" + "    parts p\n" + "    LEFT JOIN parts_orders po ON po.part_id = p.id_part\n" + "    LEFT JOIN orders o ON o.id_order = po.order_id\n" + "    LEFT JOIN jobs_parts jp ON p.id_part = jp.part_id\n" + "    LEFT JOIN jobs j ON j.id_job = jp.job_id\n" + "WHERE\n" + "    (o.status='completed'\n" + "    AND DATE(o.order_date) >= '" + dateFrom + "' AND DATE(o.order_arrival) <= '" + dateTo+ "')\n" + "    OR (j.status='completed' AND DATE(j.create_date)>= '" + dateFrom + "' AND DATE(j.fix_date) <= '" + dateTo + "');");
        List<Object[]> results = query.getResultList();
        /*for (Object[] result : results) {
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }
        }*/
        //return ResponseEntity.status(HttpStatus.OK).body(null);
        ByteArrayInputStream bis = GeneratePdfReport.stockLevel(results);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Stock_level.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }


}
