package com.garits.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PartsOrdersDetailRepository extends CrudRepository<PartsOrdersDetail,Integer> {
    @Query(value = "select po.* from parts_orders po inner join parts p on p.id_part=po.part_id where po.order_id=?1",nativeQuery = true)
    Iterable<PartsOrdersDetail> getAllByOrderId(Integer customerId);
}
