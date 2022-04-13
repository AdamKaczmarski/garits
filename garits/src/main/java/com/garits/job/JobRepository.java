package com.garits.job;

import com.garits.customer.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Set;

public interface JobRepository extends CrudRepository<Job, Integer> {
    @Query(value = "SELECT * FROM jobs WHERE status='active'", nativeQuery = true)
    Iterable<Job> findAllActive();

    @Query(value = "SELECT * FROM jobs WHERE status='completed'", nativeQuery = true)
    Iterable<Job> findAllCompleted();

    @Query(value = "SELECT * FROM jobs WHERE status='booked'", nativeQuery = true)
    Iterable<Job> findAllBooked();

    @Query(value = "select * from customers where id_customer in (select customer_id from customers_vehicles where reg_no_id=:idRegNo)", nativeQuery = true)
    Customer findCustomerForVehicle(@Param("idRegNo") String idRegNo);

    @Query(value = "select quantity_used from jobs_parts where part_id=:idPart and job_id=:idJob", nativeQuery = true)
    Integer getQuantityOfPart(@Param("idPart") Integer idPart, @Param("idJob") Integer idJob);

    @Transactional
    @Modifying
    @Query(value = "update jobs set description_required = (select group_concat(short_description SEPARATOR '; ') from services where id_service IN (select service_id from jobs_services where job_id=:idJob))", nativeQuery = true)
    void setDescriptionReq(@Param("idJob") Integer idJob);

    @Transactional
    @Modifying
    @Query(value = "update jobs set est_time_min = (select sum(approx_time_min) from services where id_service IN (select service_id from jobs_services where job_id=:idJob))", nativeQuery = true)
    void setEstTime(Integer idJob);

    @Transactional
    @Modifying
    @Query(value = "UPDATE jobs_parts set quantity_used = :quantity where part_id = :idPart and job_id = :idJob", nativeQuery = true)
    void setQuantityOfPart(@Param("idPart") Integer idPart, @Param("idJob") Integer idJob, @Param("quantity") Integer quantityUsed);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO payments (cash_or_card,amount,create_date,payment_date,payment_due) VALUES (:cashOrCard,:amount,CURDATE(),:paymentDate,:paymentDueDate)", nativeQuery = true)
    void addPayment(@Param("cashOrCard") String cashOrCard, @Param("amount") Double amount, @Param("paymentDate") String paymentDate, @Param("paymentDueDate") String paymentDueDate);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO jobs_payments(job_id,payment_id) VALUES (:jobId,:paymentId)", nativeQuery = true)
    void linkJobToPayment(@Param("jobId") Integer jobId, @Param("paymentId") Integer paymentId);

    @Query(value = "select customer_id from customers_vehicles where reg_no_id = (select id_reg_no from vehicles where id_vehicle=:vehicleId)", nativeQuery = true)
    Integer getCustomerIdByVehicle(@Param("vehicleId") Integer vehicleId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE parts set stock_level=stock_level-:quantity where id_part=:idPart", nativeQuery = true)
    void updateStockLevel(@Param("idPart") Integer idPart, @Param("quantity") Integer quantity);

    /**
     * This adds the labour time to the job and adds VAT to the total cost
     * @param idJob
     */
    @Modifying
    @Transactional
    @Query(value = "update payments set amount =ROUND( ROUND(amount + (SELECT ROUND(j.act_time_min*(select ROUND(hourly_rate/60,2) from roles where id_role = (select role_id from users_roles where user_id = (SELECT user_id from users_jobs where job_id=:idJob))),2) from jobs j where id_job=:idJob),2) where id_payment = (SELECT payment_id from jobs_payments where job_id=:idJob)*1.2,2)", nativeQuery = true)
    void updatePriceOnFinish(@Param("idJob") Integer idJob);
    //@Query(value="INSERT INTO jobs (vehicle_id,status,created_date,description_required,est_time_min)")
}
