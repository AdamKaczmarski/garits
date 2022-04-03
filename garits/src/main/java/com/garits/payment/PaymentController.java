package com.garits.payment;

import com.garits.customer.Customer;
import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    //GET MAPPINGS

    /**
     * This method returns all payments in the system.
     *
     * @return Array of Payment objects.
     */
    @GetMapping("/payments-retails")
    public @ResponseBody
    Iterable<Payment> getAllRetailPayments() {
        Iterable<Payment> result = paymentRepository.findAllRetailPayments();
        for (Payment payment : result) {
            payment.setCustomer(new Customer(payment.getCustomer().getIdCustomer(), payment.getCustomer().getName()));
        }
        return result;
    }

    @GetMapping("/payments-jobs")
    public @ResponseBody
    Iterable<Payment> getAllJobPayments() {
        Iterable<Payment> result = paymentRepository.findAllJobPayments();
        for (Payment payment : result) {
            payment.setCustomer(new Customer(payment.getCustomer().getIdCustomer(), payment.getCustomer().getName()));
        }
        return result;
    }

    /**
     * Get single payment
     *
     * @param id - payment's id
     * @return Payment object
     */
    @GetMapping("/payments-retail/{id}")
    Payment one(@PathVariable Integer id) {
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
    Payment newRetailPayment(@RequestBody Payment newPayment) {
        return paymentRepository.save(newPayment);
    }

    @PostMapping("/payments-jobs")
    Payment newJobPayment(@RequestBody Payment newPayment) {
        return paymentRepository.save(newPayment);
    }

    //DELETE MAPPINGS

    /**
     * Deletes the payment
     *
     * @param id - payment ID
     */
    @DeleteMapping("/payments-retail/{id}")
    void deleteRetailPayment(@PathVariable Integer id) {
        paymentRepository.deleteById(id);
    }

    @DeleteMapping("/payments-jobs/{id}")
    void deleteJobPayment(@PathVariable Integer id) {
        paymentRepository.deleteById(id);

    }
}
