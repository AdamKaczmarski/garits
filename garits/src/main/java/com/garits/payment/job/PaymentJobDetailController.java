package com.garits.payment.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PaymentJobDetailController {
    @Autowired
    private PaymentJobDetailRepository paymentJobRepository;
    @GetMapping("/payments-jobsldjhfs")
    public Iterable<PaymentJobDetail> getAllPayments() {
        return paymentJobRepository.findAll();
    }
}
