package com.garits.payment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PaymentRetailDetailRepository extends CrudRepository<PaymentRetailDetail, Integer> {
    @Query(value="SELECT * FROM parts_payments WHERE payment_id = :paymentId", nativeQuery = true)
    Iterable<PaymentRetailDetail> findByPaymentId(@Param("paymentId")Integer paymentId);
}
