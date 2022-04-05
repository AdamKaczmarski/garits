package com.garits.customer;

import com.garits.customer.discounts.FlexDiscount;
import com.garits.customer.discounts.VariableDiscount;
import com.garits.exceptions.NotFound;
import org.apache.coyote.Response;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * @return
     */
    @GetMapping("/customers")
    Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    @GetMapping("/customers/short")
    Iterable<Customer> getShortInfoCustomers() {
        Iterable<Customer> customers = customerRepository.findAll();
        Set<Customer> result = new HashSet<>();
        for (Customer c : customers) {
            result.add(new Customer(c.getIdCustomer(),c.getName()));
        }
        return result;
    }
    @GetMapping("/customers/{idCustomer}")
    Customer getOneCustomer(@PathVariable Integer idCustomer) {
        return customerRepository.findById(idCustomer).orElseThrow(() -> new NotFound("Could not find customer: " + idCustomer));
    }

    @GetMapping("/customers/{idCustomer}/varDiscounts")
    Iterable<VariableDiscount> getVariableDiscounts(@PathVariable Integer idCustomer) {
        return null;
    }

    @PostMapping("/customers")
    Customer addCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @PostMapping("/customers/{idCustomer}/flexDiscount")
    Customer addFlexDiscount(@PathVariable Integer idCustomer, @RequestBody FlexDiscount fD) {
        customerRepository.addFlexDiscount(idCustomer, fD.getRangeFrom(), fD.getDiscount());
        return customerRepository.findById(idCustomer).orElseThrow(() -> new NotFound("Could not obtain customer: " + idCustomer));
    }

    @PostMapping("/customers/{idCustomer}/varDiscount")
    ResponseEntity<String> addVariableDiscount(@PathVariable Integer idCustomer, @RequestBody VariableDiscount vD) throws Exception {
/*
        Customer c = customerRepository.findById(idCustomer).orElseThrow(()->new NotFound("Could not obtain customer: "+idCustomer));
        Set<VariableDiscount>  newVd = c.getVariableDiscounts();
        newVd.add(vD);
        c.setVariableDiscounts(newVd);
        return customerRepository.save(c);
*/

        if (customerRepository.checkDuplicateVarDiscount(vD.getServiceId(), vD.getCustomerId()) ==null) {
            if (vD.getDiscount() > 100 || vD.getDiscount() >= 0) {
                customerRepository.addVarDiscount(idCustomer, vD.getServiceId(), vD.getDiscount());
                return ResponseEntity.status(HttpStatus.OK).body("Added the discount");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Discount value is incorrect");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Couldn't duplicate service discount");

        }
    }

    @PatchMapping("/customers/{idCustomer}")
    Customer editCustomer(@PathVariable Integer idCustomer, @RequestBody Customer editedCustomer) {
        Customer c = customerRepository.findById(idCustomer).orElseThrow(() -> new NotFound("Could not find customer: " + idCustomer));
        if (editedCustomer.getName() != null) c.setName(editedCustomer.getName());
        if (editedCustomer.getCity() != null) c.setCity(editedCustomer.getCity());
        if (editedCustomer.getAddress() != null) c.setAddress(editedCustomer.getAddress());
        if (editedCustomer.getPostcode() != null) c.setPostcode(editedCustomer.getPostcode());
        if (editedCustomer.getTelephoneNumber() != null) c.setTelephoneNumber(editedCustomer.getTelephoneNumber());
        if (editedCustomer.getEmail() != null) c.setEmail(editedCustomer.getEmail());
        if (editedCustomer.getFax() != null) c.setFax(editedCustomer.getFax());
        if (editedCustomer.getFixedDiscount() >= 0) c.setFixedDiscount(editedCustomer.getFixedDiscount());
        return customerRepository.save(c);
    }

    @DeleteMapping("/customers/{idCustomer}")
    void deleteCustomer(@PathVariable Integer idCustomer) {
        customerRepository.deleteById(idCustomer);
    }

    @DeleteMapping("/customers/{idCustomer}/varDiscount/{idVarDiscount}")
    void deleteVariableDiscount(@PathVariable Integer idCustomer, @PathVariable Integer idVarDiscount) {
        customerRepository.deleteVarDiscount(idCustomer, idVarDiscount);
    }

    @DeleteMapping("/customers/{idCustomer}/flexDiscount/{idFlexDiscount}")
    void deleteFlexDiscount(@PathVariable Integer idCustomer, @PathVariable Integer idFlexDiscount) {
        customerRepository.deleteFlexDiscount(idCustomer, idFlexDiscount);
    }
}
