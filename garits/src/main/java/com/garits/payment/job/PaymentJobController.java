package com.garits.payment.job;

import com.garits.customer.Customer;
import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class PaymentJobController {
    @Autowired
    private PaymentJobRepository paymentRepository;

    //GET MAPPINGS



    @GetMapping("/payments-jobs")
    public @ResponseBody
    Iterable<PaymentJob> getAllJobPayments() {
        Iterable<PaymentJob> result = paymentRepository.findAllJobPayments();
        for (PaymentJob payment : result) {
            payment.setCustomer(new Customer(payment.getCustomer().getIdCustomer(), payment.getCustomer().getName()));
        }
        return result;
    }


    //POST MAPPINGS

    /**
     * Add new payment mapping.
     *
     * @param newPayment - payment object
     * @return
     */


    @PostMapping("/payments-jobs")
    PaymentJob newJobPayment(@RequestBody PaymentJob newPayment) {
        return paymentRepository.save(newPayment);
    }

    //DELETE MAPPINGS

    /**
     * Deletes the payment
     *
     * @param id - payment ID
     */

    @DeleteMapping("/payments-jobs/{id}")
    void deleteJobPayment(@PathVariable Integer id) {
        paymentRepository.deleteById(id);

    }
}
