package com.garits.customer;

import com.garits.customer.Customer;
import com.garits.customer.discounts.VariableDiscount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface CustomerRepository extends CrudRepository<Customer,Integer> {
}
