package com.garits.payment;

import com.garits.part.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/payments")
public class PaymentRetailDetailController {
    @Autowired
    private PaymentRetailDetailRepository paymentRetailDetailRepository;

    @GetMapping("/{idPayment}/retail-details")
    public Iterable<PaymentRetailDetail> getPaymentRetailDetails(@PathVariable Integer idPayment) {
        Iterable<PaymentRetailDetail> result=paymentRetailDetailRepository.findByPaymentId(idPayment);

        for (PaymentRetailDetail x :result){
            x.setPart(new Part(x.getPart().getIdPart(),x.getPart().getPartName()));
        }

        return result;
    }
}
