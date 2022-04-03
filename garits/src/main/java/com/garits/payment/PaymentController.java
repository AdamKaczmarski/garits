package com.garits.payment;

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

    @GetMapping("/payments")
    public @ResponseBody
    Iterable<PaymentRetail> getAllPayments() {return paymentRepository.findAll();
    }

    /**
     * Get single Payment
     *
     * @param id - Payment's id
     * @return Payment object
     */
    @GetMapping("/payments/{id}")
    PaymentRetail one(@PathVariable Integer id) {
        return paymentRepository.findById(id).orElseThrow(() -> new NotFound("Could not find Payment: " + id));
    }

    //POST MAPPINGS

    /**
     * Add new payment mapping.
     *
     * @param newPayment - payment object
     * @return
     */
    @PostMapping("/payments")
    PaymentRetail newPayment(@RequestBody PaymentRetail newPayment)  {
        return paymentRepository.save(newPayment);

    }

    /**
     * Deletes the Payment
     *
     * @param id - Payment ID
     */
    @DeleteMapping("/payments/{id}")
    void deletePayment(@PathVariable Integer id) {paymentRepository.deleteById(id);
    }

}
