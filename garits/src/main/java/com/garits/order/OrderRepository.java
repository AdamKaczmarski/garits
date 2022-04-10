package com.garits.order;

import com.garits.part.Part;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface OrderRepository extends CrudRepository<Order,Integer> {
    @Query(value = "SELECT po.id_parts_order,po.quantity_ordered,po.order_id,p.* from parts_orders po inner join parts p on p.id_part=po.part_id where po.order_id=?1",nativeQuery = true)
    Iterable<PartsOrdersDetail> findAllOrderDetailsById(Integer orderId    );
    @Modifying
    @Transactional
    @Query(value="INSERT INTO parts_orders (part_id,order_id,quantity_ordered) values(:partId,:orderId,:quantity)",nativeQuery = true)
    Integer addPartToOrder(@Param("partId")Integer partId, @Param("orderId")Integer orderId, @Param("quantity")int quantity);
    @Modifying
    @Transactional
    @Query(value="UPDATE orders set order_amount = (select sum(p.price*po.quantity_ordered) from parts_orders po inner join parts p on p.id_part=po.part_id where po.order_id=?1) where id_order=?1",nativeQuery = true)
    Integer setOrderTotalAmount(Integer orderId);

    @Modifying
    @Transactional
    @Query(value="DELETE from parts_orders where order_id=:idOrder",nativeQuery = true)
    void deletePartsOrders(@Param("idOrder")Integer idOrder);

}
