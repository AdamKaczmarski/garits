package com.garits.job;

import com.garits.customer.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
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
    @Transactional
    @Modifying
    @Query(value="update jobs set description_required = (select group_concat(short_description SEPARATOR '; ') from services where id_service IN (select service_id from jobs_services where job_id=:idJob))", nativeQuery = true)
    void setDescriptionReq(@Param("idJob")Integer idJob);
    @Transactional
    @Modifying
    @Query(value="update jobs set est_time_min = (select sum(approx_time_min) from services where id_service IN (select service_id from jobs_services where job_id=:idJob))", nativeQuery = true)
    void setEstTime(Integer idJob);
    //@Query(value="INSERT INTO jobs (vehicle_id,status,created_date,description_required,est_time_min)")
}
