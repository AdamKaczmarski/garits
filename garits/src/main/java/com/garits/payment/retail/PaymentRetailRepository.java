package com.garits.payment.retail;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PaymentRetailRepository extends CrudRepository<PaymentRetail, Integer> {
    @Query(value="SELECT p.* FROM payments p LEFT JOIN payments_customer pc on pc.payment_id=p.id_payment WHERE pc.payment_id IS NULL", nativeQuery = true)
    Iterable<PaymentRetail> findAllRetailPayments();
    @Modifying
    @Transactional
    @Query(value="INSERT INTO parts_payments (part_id,payment_id,quantity_sold) values(:partId,:paymentId,:quantity)",nativeQuery = true)
    Integer addPartToPayment(@Param("partId")Integer partId, @Param("paymentId")Integer paymentId, @Param("quantity")int quantity);
    @Modifying
    @Transactional
    @Query(value="UPDATE payments set amount = (select sum(p.price*pp.quantity_sold) from parts_payments pp inner join parts p on p.id_part=pp.part_id where pp.payment_id=?1) where id_payment=?1",nativeQuery = true)
    Integer setPaymentTotalAmount(Integer orderId);

}
