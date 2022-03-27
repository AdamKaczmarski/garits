package com.garits.payment;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Integer idPayment;
    @Column(name = "cash_or_card")
    private String cashOrCard;
    @Column(name = "amount")
    private double amount;
    @Column(name = "create_date")
    private LocalDate createDate;
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    @Column(name = "payment_due")
    private LocalDate paymentDue;
    //Getters and Setters

    public Integer getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public String getCashOrCard() {
        return cashOrCard;
    }

    public void setCashOrCard(String cashOrCard) {
        this.cashOrCard = cashOrCard;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getPaymentDue() {
        return paymentDue;
    }

    public void setPaymentDue(LocalDate paymentDue) {
        this.paymentDue = paymentDue;
    }
}
