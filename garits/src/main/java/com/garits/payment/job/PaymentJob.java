package com.garits.payment.job;

import com.fasterxml.jackson.annotation.JsonInclude;
import  com.garits.customer.Customer;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "payments")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PaymentJob {
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
    @OneToOne
    @JoinTable(name="payments_customer",joinColumns = @JoinColumn(name="payment_id"),inverseJoinColumns = @JoinColumn(name="customer_id"))
    private Customer customer;
    @Transient
    private Integer jobId;
    //Getters and Setters

    public PaymentJob() {
    }

    public PaymentJob(String cashOrCard, double amount, Date createDate, Date paymentDate, Date paymentDue, Customer customer) {
        this.cashOrCard = cashOrCard;
        this.amount = amount;
        this.createDate = createDate;
        this.paymentDate = paymentDate;
        this.paymentDue = paymentDue;
        this.customer = customer;
    }

    public PaymentJob(String cashOrCard, double amount, Date createDate, Date paymentDate, Date paymentDue) {
        this.cashOrCard = cashOrCard;
        this.amount = amount;
        this.createDate = createDate;
        this.paymentDate = paymentDate;
        this.paymentDue = paymentDue;
    }

    public PaymentJob(Integer idPayment, String cashOrCard, double amount, Date createDate, Date paymentDate, Date paymentDue, Customer customer) {
        this.idPayment = idPayment;
        this.cashOrCard = cashOrCard;
        this.amount = amount;
        this.createDate = createDate;
        this.paymentDate = paymentDate;
        this.paymentDue = paymentDue;
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
}
