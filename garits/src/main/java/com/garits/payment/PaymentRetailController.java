package com.garits.payment;

import com.garits.customer.Customer;
import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class PaymentRetailController {
    @Autowired
    private PaymentRetailRepository paymentRepository;

    //GET MAPPINGS

    /**
     * This method returns all payments in the system.
     *
     * @return Array of Payment objects.
     */
    @GetMapping("/payments-retail")
    public @ResponseBody
    Iterable<PaymentRetail> getAllPayments(){
        Iterable<PaymentRetail> result = paymentRepository.findAll();
        for (PaymentRetail payment : result) {
            payment.setCustomer(new Customer(payment.getCustomer().getIdCustomer(),payment.getCustomer().getName()));
        }
        return result;}

    /**
     * Get single payment
     *
     * @param id - payment's id
     * @return Payment object
     */
    @GetMapping("/payments-retail/{id}")
    PaymentRetail one(@PathVariable Integer id) {
        return paymentRepository.findById(id).orElseThrow(() -> new NotFound("Could not find payment" +id));
    }
    //POST MAPPINGS

    /**
     * Add new payment mapping.
     *
     * @param newPayment - payment object
     * @return
     */

    @PostMapping("/payments-retail")
    PaymentRetail newPayment(@RequestBody PaymentRetail newPayment) {return paymentRepository.save(newPayment); }

    //DELETE MAPPINGS

    /**
     * Deletes the payment
     *
     * @param id - payment ID
     */
    @DeleteMapping("/payments-retail/{id}")
    void deletePayment(@PathVariable Integer id) {paymentRepository.deleteById(id);
    }
}
