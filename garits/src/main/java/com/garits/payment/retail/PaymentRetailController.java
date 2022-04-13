package com.garits.payment.retail;

import com.garits.customer.Customer;
import com.garits.exceptions.NotFound;
import com.garits.order.PartOrder;
import com.garits.part.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class PaymentRetailController {
    @Autowired
    private PaymentRetailRepository paymentRepository;
    @Autowired
    private PartRepository partRepository;
    //GET MAPPINGS

    /**
     * This method returns all payments in the system.
     *
     * @return Array of Payment objects.
     */
    @GetMapping("/payments-retails")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    public @ResponseBody
    Iterable<PaymentRetail> getAllRetailPayments() {
        Iterable<PaymentRetail> result = paymentRepository.findAllRetailPayments();
/*        for (PaymentRetail payment : result) {
            payment.setCustomer(new Customer(payment.getCustomer().getIdCustomer(), payment.getCustomer().getName()));
        }*/
        return result;
    }


    /**
     * Get single payment
     *
     * @param id - payment's id
     * @return Payment object
     */
    @GetMapping("/payments-retail/{id}")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    PaymentRetail one(@PathVariable Integer id) {
        return paymentRepository.findById(id).orElseThrow(() -> new NotFound("Could not find payment" + id));
    }
    //POST MAPPINGS

    /**
     * Add new payment mapping.
     *
     * @param newPayment - payment object
     * @return
     */

    @PostMapping("/payments-retails")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    PaymentRetail newRetailPayment(@RequestBody PaymentRetail newPayment) {
        return paymentRepository.save(newPayment);
    }

    @PostMapping("/payments-retail/{idPayment}/items")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    ResponseEntity<String> addItemsToOrder(@PathVariable Integer idPayment, @RequestBody List<PartOrder> pos) {
        for (PartOrder x : pos) {
            paymentRepository.addPartToPayment(x.getPartId(), idPayment, x.getQuantity());
            partRepository.updatePartStockFromRetailPayment(x.getPartId(), idPayment);
        }
        paymentRepository.setPaymentTotalAmount(idPayment);
        return ResponseEntity.ok("ok");
    }


    //DELETE MAPPINGS

    /**
     * Deletes the payment
     *
     * @param id - payment ID
     */
    @DeleteMapping("/payments-retail/{id}")
    @PreAuthorize(" hasRole('FRANCHISEE')")
    void deleteRetailPayment(@PathVariable Integer id) {
        paymentRepository.deleteById(id);
    }

}
