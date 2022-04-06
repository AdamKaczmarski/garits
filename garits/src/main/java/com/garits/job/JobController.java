package com.garits.job;

import com.garits.customer.CustomerRepository;
import com.garits.exceptions.NotFound;
import com.garits.part.Part;
import com.garits.user.User;
import com.garits.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.sql.Timestamp;

@RestController
@RequestMapping(path = "/")
public class JobController {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CustomerRepository customerRepository;
    //GET MAPPINGS
    //Those 3 methods should return only data neeeded for the frontend without any details.
    @GetMapping("/jobs-active")
    Iterable<Job> getAllActiveJobs() {
        Iterable<Job> result =  jobRepository.findAllActive();
        for (Job j : result) {
            j.getVehicle().setCustomer(customerRepository.findCustomerForVehicle(j.getVehicle().getIdRegNo()));
            User u = new User(j.getUser().iterator().next().getIdUser(),j.getUser().iterator().next().getFirstName(),j.getUser().iterator().next().getLastName());
            j.getUser().clear();
            j.getUser().add(u);
            j.setServices(null);
            j.setParts(null);
        }
        return result;
    }
    @GetMapping("/jobs-completed")
    Iterable<Job> getAllCompletedJobs() {
        Iterable<Job> result =  jobRepository.findAllCompleted();
        for (Job j : result) {
            j.getVehicle().setCustomer(customerRepository.findCustomerForVehicle(j.getVehicle().getIdRegNo()));
            User u = new User(j.getUser().iterator().next().getIdUser(),j.getUser().iterator().next().getFirstName(),j.getUser().iterator().next().getLastName());
            j.getUser().clear();
            j.getUser().add(u);
            j.setServices(null);
            j.setParts(null);
        }
        return result;
    }
    @GetMapping("/jobs-booked")
    Iterable<Job> getAllBookedJobs() {
        Iterable<Job> result =  jobRepository.findAllBooked();
        for (Job j : result) {
            j.getVehicle().setCustomer(customerRepository.findCustomerForVehicle(j.getVehicle().getIdRegNo()));
            j.setServices(null);
            j.setParts(null);
        }
        return result;
    }

    @GetMapping("/jobs/{idJob}")
    Job getOneJob(@PathVariable Integer idJob) {
        Job j =jobRepository.findById(idJob).orElseThrow(() -> new NotFound("Could not find job: " + idJob));
        j.getVehicle().setCustomer(customerRepository.findCustomerForVehicle(j.getVehicle().getIdRegNo()));
        if (!j.getStatus().equals("booked")) {
            User u = new User(j.getUser().iterator().next().getIdUser(), j.getUser().iterator().next().getFirstName(), j.getUser().iterator().next().getLastName());
            j.getUser().clear();
            j.getUser().add(u);
        }
        for (Part p : j.getParts()) {
            p.setQuantityUsed(jobRepository.getQuantityOfPart(p.getIdPart(),j.getIdJob()));
        }
        return j;
    }

    //POST MAPPINGS
    @PostMapping("/jobs")
    Job addJob(@RequestBody Job newJob) {
        newJob.setCreateDate(new Timestamp(System.currentTimeMillis()));
        Job j =  jobRepository.save(newJob);
        jobRepository.setDescriptionReq(j.getIdJob());
        jobRepository.setEstTime(j.getIdJob());
        return j;
    }

    //PUT MAPPINGS
    @PatchMapping("/jobs/{idJob}")
    Job editJob(@PathVariable Integer idJob, @RequestBody Job editedJob) {
        Job j = jobRepository.findById(idJob).orElseThrow(() -> new NotFound("Could not find job: " + idJob));
        if (editedJob.getStatus() != null) j.setStatus(editedJob.getStatus());
        if (editedJob.getDescriptionDone()!=null &&!editedJob.getDescriptionDone().equals(j.getDescriptionDone()))
            j.setDescriptionDone(editedJob.getDescriptionDone());
        if (editedJob.getDescriptionRequired()!=null&&!editedJob.getDescriptionRequired().equals(j.getDescriptionRequired()))
            j.setDescriptionRequired(editedJob.getDescriptionRequired());
        if (editedJob.getEstTimeMin() > 0) j.setEstTimeMin(editedJob.getEstTimeMin());
        if (editedJob.getActTimeMin()!=null&&editedJob.getActTimeMin() >= 0) j.setActTimeMin(editedJob.getActTimeMin());
        if (editedJob.getBookingDate() != null) j.setBookingDate(editedJob.getBookingDate());
        if (editedJob.getFixDate() != null) j.setFixDate(editedJob.getFixDate());
        if (!editedJob.getBay().equals(j.getBay())) j.setBay(editedJob.getBay());
        if (editedJob.getUser() != null) j.setUser(editedJob.getUser());
        return jobRepository.save(j);
    }

    //DELETE MAPPINGS
    @DeleteMapping("/jobs/{idJob}")
    void deleteJob(@PathVariable Integer idJob) {
        jobRepository.deleteById(idJob);
    }
}
