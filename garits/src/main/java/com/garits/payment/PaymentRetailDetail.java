package com.garits.payment;

import com.garits.part.Part;

import javax.persistence.*;

@Entity
@Table(name="parts_payments")
public class PaymentRetailDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_parts_payments")
    private Integer idPartsPayments;
    @Column(name="quantity_sold")
    private Integer quantitySold;
    @Column(name="payment_id")
    private Integer paymentId;
    @ManyToOne
    @JoinColumn(name="part_id")
    private Part part;

    public Integer getIdPartsPayments() {
        return idPartsPayments;
    }

    public Integer getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Integer quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
