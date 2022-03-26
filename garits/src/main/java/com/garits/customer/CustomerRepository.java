package com.garits.customer;

import com.garits.customer.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
}
