package com.garits.payment.job;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;

public interface PaymentJobRepository extends CrudRepository<PaymentJob, Integer> {

    @Query(value="SELECT p.*,pc.* FROM payments p INNER JOIN payments_customer pc on pc.payment_id=p.id_payment WHERE p.id_payment IN (select distinct payment_id from jobs_payments jb RIGHT JOIN payments p ON jb.payment_id = p.id_payment where jb.payment_id IS NOT NULL)", nativeQuery = true)
    Iterable<PaymentJob> findAllJobPayments();
    @Query(value="SELECT job_id from jobs_payments where payment_id=:idPayment",nativeQuery = true)
    Integer findJobId(@Param("idPayment") Integer idPayment);

    @Modifying
    @Transactional
    @Query(value="UPDATE payments SET payment_date=:paymentDate, cash_or_card=:cashOrCard where id_payment=:idPayment",nativeQuery = true)
    void finishPayment(@Param("idPayment")Integer id,@Param("paymentDate") Date paymentDate,@Param("cashOrCard") String cashOrCard);
}
