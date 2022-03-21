package com.garits.job;

import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.sql.Timestamp;

@RestController
@RequestMapping(path = "/")
public class JobController {
    @Autowired
    private JobRepository jobRepository;

    //GET MAPPINGS
    @GetMapping("/jobs")
    Iterable<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/jobs/{idJob}")
    Job getOneJob(@PathVariable Integer idJob) {
        return jobRepository.findById(idJob).orElseThrow(() -> new NotFound("Could not find job: " + idJob));
    }

    //POST MAPPINGS
    @PostMapping("/jobs")
    Job addJob(@RequestBody Job newJob) {
        newJob.setCreateDate(new Timestamp(System.currentTimeMillis()));
        return jobRepository.save(newJob);
    }

    //PUT MAPPINGS
    @PutMapping("/jobs/{idJob}")
    Job editJob(@PathVariable Integer idJob, @RequestBody Job editedJob) {
        Job j = jobRepository.findById(idJob).orElseThrow(() -> new NotFound("Could not find job: " + idJob));
        if (editedJob.getStatus() != null) j.setStatus(editedJob.getStatus());
        if (!editedJob.getDescriptionDone().equals(j.getDescriptionDone()))
            j.setDescriptionDone(editedJob.getDescriptionDone());
        if (!editedJob.getDescriptionRequired().equals(j.getDescriptionRequired()))
            j.setDescriptionRequired(editedJob.getDescriptionRequired());
        if (editedJob.getEstTimeMin() > 0) j.setEstTimeMin(editedJob.getEstTimeMin());
        if (editedJob.getActTimeMin() >= 0) j.setActTimeMin(editedJob.getActTimeMin());
        if (editedJob.getBookingDate() != null) j.setBookingDate(editedJob.getBookingDate());
        if (editedJob.getFixDate() != null) j.setFixDate(editedJob.getFixDate());
        if (!editedJob.getBay().equals(j.getBay())) j.setBay(editedJob.getBay());
        return jobRepository.save(j);
    }

    //DELETE MAPPINGS
    @DeleteMapping("/jobs/{idJob}")
    void deleteJob(@PathVariable Integer idJob) {
        jobRepository.deleteById(idJob);
    }
}
