package com.garits.payment.retail;

import com.garits.part.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments-retails/")
public class PaymentRetailDetailController {
    @Autowired
    private PaymentRetailDetailRepository paymentRetailDetailrepository;
    @GetMapping("/{paymentId}/details")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Iterable<PaymentRetailDetail> getAllDetailsByPaymentId(@PathVariable Integer paymentId) {
        Iterable<PaymentRetailDetail> result = paymentRetailDetailrepository.findAllByPaymentId(paymentId);
        for (PaymentRetailDetail x : result) {
            x.setPart(new Part(x.getPart().getIdPart(),x.getPart().getPartName(),x.getPart().getPrice(),x.getPart().getCode()));
        }
        return result;
    }
}
