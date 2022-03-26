package com.garits.customer;

import com.garits.customer.Customer;
import com.garits.customer.discounts.VariableDiscount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.transaction.Transactional;


public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO customer_variable_discounts_services (customer_id,service_id,discount) VALUES (:idCustomer,:serviceId,:discount)",nativeQuery = true)
    void addVarDiscount(@Param("idCustomer") Integer idCustomer,@Param("serviceId") Integer serviceId, @Param("discount") int discount);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO customer_flex_discounts (customer_id,range_from,discount) VALUES (:idCustomer,:rangeFrom,:discount)",nativeQuery = true)
    void addFlexDiscount(@Param("idCustomer") Integer idCustomer,@Param("rangeFrom") int rangeFrom, @Param("discount") int discount);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customer_variable_discounts_services where customer_id=:idCustomer and id_var_discount=:idVarDiscount",nativeQuery = true)
    void deleteVarDiscount(@Param("idCustomer")Integer idCustomer,@Param("idVarDiscount") Integer idVarDiscount);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customer_flex_discounts where customer_id=:idCustomer and id_flex_discount=:idFlexDiscount",nativeQuery = true)
    void deleteFlexDiscount(@Param("idCustomer")Integer idCustomer,@Param("idFlexDiscount") Integer idFlexDiscount);
}
