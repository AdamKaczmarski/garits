package com.garits.payment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    @Query(value="SELECT p.*,pc.* FROM payments p INNER JOIN payments_customer pc on pc.payment_id=p.id_payment WHERE p.id_payment IN (select distinct payment_id from parts_payments pp RIGHT JOIN payments p ON pp.payment_id = p.id_payment where pp.payment_id IS NOT NULL)", nativeQuery = true)
    Iterable<Payment> findAllRetailPayments();

    @Query(value="SELECT p.*,pc.* FROM payments p INNER JOIN payments_customer pc on pc.payment_id=p.id_payment WHERE p.id_payment IN (select distinct payment_id from jobs_payments jb RIGHT JOIN payments p ON jb.payment_id = p.id_payment where jb.payment_id IS NOT NULL)", nativeQuery = true)
    Iterable<Payment> findAllJobPayments();
}
