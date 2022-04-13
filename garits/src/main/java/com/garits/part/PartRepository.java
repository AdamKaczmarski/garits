package com.garits.part;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PartRepository extends CrudRepository<Part,Integer> {
    @Query(value = "select id_part, part_name from parts",nativeQuery = true)
    Iterable<Part> allPartNames();
    @Query(value="SELECT * FROM parts where id_part IN (SELECT part_id FROM parts_orders where order_id=?1)",nativeQuery = true)
    Iterable<Part> findPartsByOrderId(Integer idOrder);
    @Modifying
    @Transactional
    @Query(value="UPDATE parts set stock_level=stock_level+(SELECT quantity_ordered from parts_orders where part_id=:partId and order_id=:orderId) where id_part=:partId",nativeQuery = true)
    Integer updatePartStockFromOrder(@Param("partId")Integer partId, @Param("orderId")Integer orderId);
    @Modifying
    @Transactional
    @Query(value="UPDATE parts set stock_level=stock_level-(SELECT quantity_sold from parts_payments where part_id=:partId and payment_id=:paymentId) where id_part=:partId",nativeQuery = true)
    Integer updatePartStockFromRetailPayment(@Param("partId")Integer partId, @Param("paymentId")Integer paymentId);
    @Query(value = "select * from parts where stock_level<stock_level_threshold",nativeQuery = true)
    Iterable<Part> findLowStockParts();
}
