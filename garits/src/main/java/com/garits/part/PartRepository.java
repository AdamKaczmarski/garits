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
    Integer updatePartStock(@Param("partId")Integer partId, @Param("orderId")Integer orderId);

}
