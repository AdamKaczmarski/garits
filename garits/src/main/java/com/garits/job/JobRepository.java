package com.garits.job;

import com.garits.customer.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface JobRepository extends CrudRepository<Job,Integer> {
    @Query(value="SELECT * FROM jobs WHERE status='active'", nativeQuery = true)
    Iterable<Job> findAllActive();
    @Query(value="SELECT * FROM jobs WHERE status='completed'", nativeQuery = true)
    Iterable<Job> findAllCompleted();
    @Query(value="SELECT * FROM jobs WHERE status='booked'", nativeQuery = true)
    Iterable<Job> findAllBooked();
    @Query (value="select * from customers where id_customer in (select customer_id from customers_vehicles where reg_no_id=:idRegNo)", nativeQuery = true)
    Customer findCustomerForVehicle(@Param("idRegNo") String idRegNo);
    @Query(value="select quantity_used from jobs_parts where part_id=:idPart and job_id=:idJob", nativeQuery = true)
    Integer getQuantityOfPart(@Param("idPart") Integer idPart,@Param("idJob") Integer idJob);
}
