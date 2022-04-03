package com.garits.payment;

import com.garits.part.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments-retail/")
public class PaymentRetailDetailController {
    @Autowired
    private PaymentRetailDetailRepository paymentRetailDetailrepository;
    @GetMapping("/{paymentId}/details")
    Iterable<PaymentRetailDetail> getAllDetailsByPaymentId(@PathVariable Integer paymentId) {
        Iterable<PaymentRetailDetail> result = paymentRetailDetailrepository.findAllByPaymentId(paymentId);
        for (PaymentRetailDetail x : result) {
            x.setPart(new Part(x.getPart().getIdPart(),x.getPart().getCode(),x.getPart().getPrice()));
        }
        return result;
    }
}
