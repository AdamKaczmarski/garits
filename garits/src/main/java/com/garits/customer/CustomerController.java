package com.garits.customer;

import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    /**
     *
     * @return
     */
    @GetMapping("/customers")
    Iterable<Customer> getAllCustomers() {
       return customerRepository.findAll();
    }

    @GetMapping("/customers/{idCustomer}")
    Customer getOneCustomer(@PathVariable Integer idCustomer) {
        return customerRepository.findById(idCustomer).orElseThrow(() -> new NotFound("Could not find customer: " + idCustomer));
    }

    @PostMapping("/customers")
    Customer addCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @PutMapping("/customers/{idCustomer}")
    Customer editCustomer(@PathVariable Integer idCustomer, @RequestBody Customer editedCustomer) {
        Customer c = customerRepository.findById(idCustomer).orElseThrow(() -> new NotFound("Could not find customer: " + idCustomer));
        if (editedCustomer.getName() != null) c.setName(editedCustomer.getName());
        if (editedCustomer.getCity() != null) c.setCity(editedCustomer.getCity());
        if (editedCustomer.getAddress() != null) c.setAddress(editedCustomer.getAddress());
        if (editedCustomer.getPostcode() != null) c.setPostcode(editedCustomer.getPostcode());
        if (editedCustomer.getTelephoneNumber() != null) c.setTelephoneNumber(editedCustomer.getTelephoneNumber());
        if (editedCustomer.getEmail() != null) c.setEmail(editedCustomer.getEmail());
        if (editedCustomer.getFax() != null) c.setFax(editedCustomer.getFax());
        if (editedCustomer.getFixedDiscount()>=0) c.setFixedDiscount(editedCustomer.getFixedDiscount());
        return c;
    }
}
