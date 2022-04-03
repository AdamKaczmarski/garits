package com.garits.payment;

import com.garits.customer.Customer;

import javax.persistence.*;
import java.sql.Date;

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
    private Date createDate;
    @Column(name = "payment_date")
    private Date paymentDate;
    @Column(name = "payment_due")
    private Date paymentDue;
    @OneToOne
    @JoinTable(name="payments_customer",joinColumns = @JoinColumn(name="payment_id"),inverseJoinColumns = @JoinColumn(name="customer_id"))
    private Customer customer;
    //Getters and Setters

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
}
