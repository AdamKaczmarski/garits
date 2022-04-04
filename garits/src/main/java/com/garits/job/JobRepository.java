package com.garits.job;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job,Integer> {
    @Query(value="SELECT * FROM jobs WHERE status='active'", nativeQuery = true)
    Iterable<Job> findAllActive();
    @Query(value="SELECT * FROM jobs WHERE status='completed'", nativeQuery = true)
    Iterable<Job> findAllCompleted();
    @Query(value="SELECT * FROM jobs WHERE status='booked'", nativeQuery = true)
    Iterable<Job> findAllBooked();
}
