package com.garits.job;

import com.garits.customer.Customer;
import com.garits.customer.CustomerRepository;
import com.garits.customer.discounts.FlexDiscount;
import com.garits.customer.discounts.VariableDiscount;
import com.garits.exceptions.NotFound;
import com.garits.part.Part;
import com.garits.payment.job.PaymentJob;
import com.garits.payment.job.PaymentJobRepository;
import com.garits.service.Service;
import com.garits.service.ServiceRepository;
import com.garits.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/")
public class JobController {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PaymentJobRepository paymentRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    //GET MAPPINGS
    //Those 3 methods should return only data neeeded for the frontend without any details.

    /**
     *
     * @return returns only an array of jobs that are currently active
     */
    @GetMapping("/jobs-active")
    @PreAuthorize("hasRole('RECEPTIONIST')  or hasRole('MECHANIC') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Iterable<Job> getAllActiveJobs() {
        Iterable<Job> result = jobRepository.findAllActive();
        for (Job j : result) {
            j.getVehicle().setCustomer(customerRepository.findCustomerForVehicle(j.getVehicle().getIdRegNo()));
            User u = new User(j.getUser().iterator().next().getIdUser(), j.getUser().iterator().next().getFirstName(), j.getUser().iterator().next().getLastName());
            j.getUser().clear();
            j.getUser().add(u);
            j.setServices(null);
            j.setParts(null);
        }
        return result;
    }

    /**
     *
     * @return returns the array of job objects of those that are completed
     */
    @GetMapping("/jobs-completed")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Iterable<Job> getAllCompletedJobs() {
        Iterable<Job> result = jobRepository.findAllCompleted();
        for (Job j : result) {
            j.getVehicle().setCustomer(customerRepository.findCustomerForVehicle(j.getVehicle().getIdRegNo()));
            User u = new User(j.getUser().iterator().next().getIdUser(), j.getUser().iterator().next().getFirstName(), j.getUser().iterator().next().getLastName());
            j.getUser().clear();
            j.getUser().add(u);
            j.setServices(null);
            j.setParts(null);
        }
        return result;
    }

    /**
     *
     * @return  array of job objects that are pending/booked
     */
    @GetMapping("/jobs-booked")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('MECHANIC') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Iterable<Job> getAllBookedJobs() {
        Iterable<Job> result = jobRepository.findAllBooked();
        for (Job j : result) {
            j.getVehicle().setCustomer(customerRepository.findCustomerForVehicle(j.getVehicle().getIdRegNo()));
            j.setServices(null);
            j.setParts(null);
        }
        return result;
    }

    /**
     * Get single job details
     * @param idJob
     * @return
     */
    @GetMapping("/jobs/{idJob}")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('MECHANIC') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Job getOneJob(@PathVariable Integer idJob) {
        Job j = jobRepository.findById(idJob).orElseThrow(() -> new NotFound("Could not find job: " + idJob));
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
        return j;
    }

    //POST MAPPINGS

    /**
     * Save a new job and set its price => craete a new payment record
     * @param newJob
     * @return
     */
    @PostMapping("/jobs")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Job addJob(@RequestBody Job newJob) {
        newJob.setCreateDate(new Timestamp(System.currentTimeMillis()));
        Job j = jobRepository.save(newJob);
        jobRepository.setDescriptionReq(j.getIdJob());
        jobRepository.setEstTime(j.getIdJob());
        Optional<Customer> customer  = customerRepository.findById(jobRepository.getCustomerIdByVehicle(j.getVehicle().getIdVehicle()));
        Set<VariableDiscount> variableDiscounts = customer.get().getVariableDiscounts();

        double total=0;
        for (Service s : j.getServices()) {
            Optional<Service> tmp = serviceRepository.findById(s.getIdService());
            if (tmp.isPresent()) {
                if (variableDiscounts.size() <= 0) {
                    total += tmp.get().getServicePrice();
                }
                for (VariableDiscount vd : variableDiscounts) {
                    if (vd.getServiceId() == tmp.get().getIdService()) {
                        total+=tmp.get().getServicePrice() * (100 - vd.getDiscount());
                        total=total/100;
                    } else {
                        total += tmp.get().getServicePrice();
                    }
                }
            }

        }
        Set<FlexDiscount> flexDiscounts = customer.get().getFlexDiscounts();
        double total2 = total;
        for (FlexDiscount fd : flexDiscounts) {
            if (total > fd.getRangeFrom()) {
                total2=total;
                total2 = total * (100 - fd.getDiscount());
                total2=total2/100;
            }
        }
        total=total2;
        PaymentJob p = paymentRepository.save(new PaymentJob(null,total, new Date(System.currentTimeMillis()),null,null,customer.get()));
        jobRepository.linkJobToPayment(j.getIdJob(), p.getIdPayment());
        //Integer idPayment = paymentRepository.findById()
         //   jobRepository.linkCustomerToPayment(idCustomer, j.getIdJob());
        return j;
    }

    //PATCH MAPPINGS

    /**
     * Edit the job details
     * @param idJob
     * @param editedJob
     * @return
     */
    @PatchMapping("/jobs/{idJob}")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('MECHANIC') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Job editJob(@PathVariable Integer idJob, @RequestBody Job editedJob) {
        Job j = jobRepository.findById(idJob).orElseThrow(() -> new NotFound("Could not find job: " + idJob));
        if (editedJob.getStatus() != null) j.setStatus(editedJob.getStatus());
        if (editedJob.getDescriptionDone() != null && !editedJob.getDescriptionDone().equals(j.getDescriptionDone()))
            j.setDescriptionDone(editedJob.getDescriptionDone());
        if (editedJob.getDescriptionRequired() != null && !editedJob.getDescriptionRequired().equals(j.getDescriptionRequired()))
            j.setDescriptionRequired(editedJob.getDescriptionRequired());
        if (editedJob.getEstTimeMin() > 0) j.setEstTimeMin(editedJob.getEstTimeMin());
        if (editedJob.getActTimeMin() != null && editedJob.getActTimeMin() >= 0)
            j.setActTimeMin(editedJob.getActTimeMin());
        if (editedJob.getBookingDate() != null) j.setBookingDate(editedJob.getBookingDate());
        if (editedJob.getFixDate() != null) j.setFixDate(editedJob.getFixDate());
        if (editedJob.getBay()!=null) j.setBay(editedJob.getBay());
        if (editedJob.getUser() != null) j.setUser(editedJob.getUser());
        if (editedJob.getParts() != null) {
            j.setParts(editedJob.getParts());
        }

        if (j.getActTimeMin() != null && j.getActTimeMin() >= 0 && j.getStatus().equals("completed")) {

            Optional<Customer> customer  = customerRepository.findById(jobRepository.getCustomerIdByVehicle(j.getVehicle().getIdVehicle()));

            if (customer.isPresent()) {
                if (customer.get().isAccountHolder()){

                    jobRepository.setPaymentDate30(j.getIdJob());
                }else {

                    jobRepository.setPaymentDate5(j.getIdJob());
                }
            } else {

                jobRepository.setPaymentDate5(j.getIdJob());
            }
            jobRepository.updatePriceOnFinish(j.getIdJob());
        }
        return jobRepository.save(j);
    }

    /**
     * Add quantity of the parts used
     * @param idJob
     * @param idPart
     * @param quantity
     */
    @PatchMapping("/jobs/{idJob}/{idPart}/{quantity}")
    @PreAuthorize("hasRole('RECEPTIONIST')  or hasRole('MECHANIC') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    void addQuantity(@PathVariable Integer idJob, @PathVariable Integer idPart, @PathVariable Integer quantity) {
        jobRepository.setQuantityOfPart(idPart, idJob, quantity);
        jobRepository.updateStockLevel(idPart,quantity);
    }
    //DELETE MAPPINGS

    /**
     * delete a job
     * @param idJob
     */
    @DeleteMapping("/jobs/{idJob}")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    void deleteJob(@PathVariable Integer idJob) {
        jobRepository.deleteById(idJob);
    }
}
