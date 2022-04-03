package com.garits.payment;

import com.garits.exceptions.NotFound;
import com.garits.user.User;
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
    Iterable<Payment> getAllPayments(){return paymentRepository.findAll();}

    /**
     * Get single payment
     *
     * @param id - payment's id
     * @return Payment object
     */
    @GetMapping("payments/{id}")
    Payment one(@PathVariable Integer id) {
        return paymentRepository.findById(id).orElseThrow(() -> new NotFound("Could not find payment" +id));
    }
    //POST MAPPINGS

    /**
     * Add new payment mapping.
     *
     * @param newPayment - payment object
     * @return
     */

    @PostMapping("/user")

        Payment newPayment(@RequestBody Payment newPayment) {return paymentRepository.save(newPayment); }

    //DELETE MAPPINGS

    /**
     * Deletes the payment
     *
     * @param id - payment ID
     */
    @DeleteMapping("/payments/{id}")
    void deletePayment(@PathVariable Integer id) {paymentRepository.deleteById(id);
    }
}
