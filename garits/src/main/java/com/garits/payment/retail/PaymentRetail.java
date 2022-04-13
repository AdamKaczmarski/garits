package com.garits.payment.retail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.garits.customer.Customer;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "payments")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PaymentRetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Integer idPayment;
    @Column(name = "cash_or_card")
    private String cashOrCard;
    @Column(name = "amount")
    private double amount;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "payment_date")
    private Date paymentDate;
    @Column(name = "payment_due")
    private Date paymentDue;

    //Getters and Setters

    public PaymentRetail() {
    }

    public PaymentRetail(String cashOrCard, double amount, Date createDate, Date paymentDate, Date paymentDue) {
        this.cashOrCard = cashOrCard;
        this.amount = amount;
        this.createDate = createDate;
        this.paymentDate = paymentDate;
        this.paymentDue = paymentDue;
    }

    public PaymentRetail(Integer idPayment, String cashOrCard, double amount, Date createDate, Date paymentDate, Date paymentDue) {
        this.idPayment = idPayment;
        this.cashOrCard = cashOrCard;
        this.amount = amount;
        this.createDate = createDate;
        this.paymentDate = paymentDate;
        this.paymentDue = paymentDue;
    }

    public Integer getIdPayment() {
        return idPayment;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getPaymentDue() {
        return paymentDue;
    }

    public void setPaymentDue(Date paymentDue) {
        this.paymentDue = paymentDue;
    }

}