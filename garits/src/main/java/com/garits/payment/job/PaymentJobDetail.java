package com.garits.payment.job;

import javax.persistence.*;

@Entity
@Table(name="jobs_payments")
public class PaymentJobDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private Integer paymentId;

}
