package com.garits.customer;

import com.garits.customer.discounts.FlexDiscount;
import com.garits.customer.discounts.VariableDiscount;
import com.garits.exceptions.NotFound;
import org.apache.coyote.Response;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/short")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Iterable<Customer> getShortInfoCustomers() {
        Iterable<Customer> customers = customerRepository.findAll();
        Set<Customer> result = new HashSet<>();
        for (Customer c : customers) {
            result.add(new Customer(c.getIdCustomer(), c.getName()));
        }
        return result;
    }

    @GetMapping("/customers/{idCustomer}")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Customer getOneCustomer(@PathVariable Integer idCustomer) {
        return customerRepository.findById(idCustomer).orElseThrow(() -> new NotFound("Could not find customer: " + idCustomer));
    }

    @GetMapping("/customers/{idCustomer}/varDiscounts")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Iterable<VariableDiscount> getVariableDiscounts(@PathVariable Integer idCustomer) {
        return null;
    }

    @GetMapping("/customers/late-payments")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Iterable<Customer> getLatePaymentCustomers() {
        Set<Customer> result = new HashSet<>();
        for (Customer c : customerRepository.findLatePaymentCustomers()) {
            result.add(new Customer(c.getIdCustomer(), c.getName(),c.isAccountHolder()));
        }
        for (Customer c : customerRepository.findLateAccountHolders()){
            result.add(new Customer(c.getIdCustomer(), c.getName(),c.isAccountHolder()));
        }
        return result;
    }

    @PostMapping("/customers")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Customer addCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @PostMapping("/customers/{idCustomer}/flexDiscount")
    @PreAuthorize(" hasRole('FRANCHISEE')")
    Customer addFlexDiscount(@PathVariable Integer idCustomer, @RequestBody FlexDiscount fD) {
        customerRepository.addFlexDiscount(idCustomer, fD.getRangeFrom(), fD.getDiscount());
        return customerRepository.findById(idCustomer).orElseThrow(() -> new NotFound("Could not obtain customer: " + idCustomer));
    }

    @PostMapping("/customers/{idCustomer}/varDiscount")
    @PreAuthorize(" hasRole('FRANCHISEE')")
    ResponseEntity<String> addVariableDiscount(@PathVariable Integer idCustomer, @RequestBody VariableDiscount vD) throws Exception {
/*
        Customer c = customerRepository.findById(idCustomer).orElseThrow(()->new NotFound("Could not obtain customer: "+idCustomer));
        Set<VariableDiscount>  newVd = c.getVariableDiscounts();
        newVd.add(vD);
        c.setVariableDiscounts(newVd);
        return customerRepository.save(c);
*/

        if (customerRepository.checkDuplicateVarDiscount(vD.getServiceId(), vD.getCustomerId()) == null) {
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
    @PreAuthorize("hasRole('FRANCHISEE')")
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
    @PatchMapping("/customers/account-holding/{idCustomer}/{decision}")
    @PreAuthorize("hasRole('FRANCHISEE')")
    ResponseEntity<String> editAccountHolding(@PathVariable("idCustomer")Integer idCustomer,@PathVariable("decision") boolean decision) {
        customerRepository.findById(idCustomer).orElseThrow(() -> new NotFound("Could not find customer: " + idCustomer));
        customerRepository.updateHolding(idCustomer,decision);
        if (decision==false){
            customerRepository.deleteVarDiscounts(idCustomer);
            customerRepository.deleteFlexDiscounts(idCustomer);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Account status changed");
    }
    @DeleteMapping("/customers/{idCustomer}")
    @PreAuthorize("hasRole('FRANCHISEE')")
    void deleteCustomer(@PathVariable Integer idCustomer) {
        customerRepository.deleteById(idCustomer);
    }

    @DeleteMapping("/customers/{idCustomer}/varDiscount/{idVarDiscount}")
    @PreAuthorize("hasRole('FRANCHISEE')")
    void deleteVariableDiscount(@PathVariable Integer idCustomer, @PathVariable Integer idVarDiscount) {
        customerRepository.deleteVarDiscount(idCustomer, idVarDiscount);
    }

    @DeleteMapping("/customers/{idCustomer}/flexDiscount/{idFlexDiscount}")
    @PreAuthorize("hasRole('FRANCHISEE')")
    void deleteFlexDiscount(@PathVariable Integer idCustomer, @PathVariable Integer idFlexDiscount) {
        customerRepository.deleteFlexDiscount(idCustomer, idFlexDiscount);
    }
}
