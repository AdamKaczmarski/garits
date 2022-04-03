package com.garits.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.garits.part.Part;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parts_payments")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PaymentRetailDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parts_payments")
    private Integer idPartsPayments;
    @Column(name="payment_id")
    private Integer paymentId;
    @Column(name="quantity_sold")
    private int quantitySold;
    @OneToOne
    @JoinColumn(name = "part_id")
    private Part part;

    public PaymentRetailDetail() {
    }

    public Integer getIdPartsPayments() {
        return idPartsPayments;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
