package com.garits.customer;

import com.garits.customer.Customer;
import com.garits.customer.discounts.VariableDiscount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.transaction.Transactional;
import java.util.Set;


public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO customer_variable_discounts_services (customer_id,service_id,discount) VALUES (:idCustomer,:serviceId,:discount)", nativeQuery = true)
    void addVarDiscount(@Param("idCustomer") Integer idCustomer, @Param("serviceId") Integer serviceId, @Param("discount") int discount);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO customer_flex_discounts (customer_id,range_from,discount) VALUES (:idCustomer,:rangeFrom,:discount)", nativeQuery = true)
    void addFlexDiscount(@Param("idCustomer") Integer idCustomer, @Param("rangeFrom") int rangeFrom, @Param("discount") int discount);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customer_variable_discounts_services where customer_id=:idCustomer and id_var_discount=:idVarDiscount", nativeQuery = true)
    void deleteVarDiscount(@Param("idCustomer") Integer idCustomer, @Param("idVarDiscount") Integer idVarDiscount);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customer_flex_discounts where customer_id=:idCustomer and id_flex_discount=:idFlexDiscount", nativeQuery = true)
    void deleteFlexDiscount(@Param("idCustomer") Integer idCustomer, @Param("idFlexDiscount") Integer idFlexDiscount);

    @Query(value = "SELECT 1 from customer_variable_discounts_services where service_id=?1 and customer_id=?2", nativeQuery = true)
    Integer checkDuplicateVarDiscount(Integer serviceId, Integer customerId);

    @Query(value = "select * from customers where id_customer in (select customer_id from customers_vehicles where reg_no_id=:idRegNo)", nativeQuery = true)
    Set<Customer> findCustomerForVehicle(@Param("idRegNo") String idRegNo);

    @Query(value = "SELECT * from customers where id_customer IN (SELECT DISTINCT pc.customer_id FROM payments_customer pc INNER JOIN payments p on p.id_payment = pc.payment_id inner join customers c on c.id_customer = pc.customer_id where datediff(p.payment_due,curdate())<=5 AND p.payment_date is NULL AND c.is_account_holder=false);", nativeQuery = true)
    Iterable<Customer> findLatePaymentCustomers();
    @Query(value = "SELECT * from customers where id_customer IN (SELECT DISTINCT pc.customer_id FROM payments_customer pc INNER JOIN payments p on p.id_payment = pc.payment_id inner join customers c on c.id_customer = pc.customer_id where datediff(p.payment_due,curdate())<=30 AND p.payment_date is NULL AND c.is_account_holder=true);", nativeQuery = true)
    Iterable<Customer> findLateAccountHolders();

    @Modifying
    @Transactional
    @Query(value = "update customers set is_account_holder=?2 where id_customer=?1", nativeQuery = true)
    void updateHolding(Integer idCustomer, boolean decision);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customer_variable_discounts_services where customer_id=:idCustomer", nativeQuery = true)
    void deleteVarDiscounts(@Param("idCustomer") Integer idCustomer);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customer_flex_discounts where customer_id=:idCustomer", nativeQuery = true)
    void deleteFlexDiscounts(@Param("idCustomer") Integer idCustomer);

}
