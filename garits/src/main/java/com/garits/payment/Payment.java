package com.garits.payment;

import javax.persistence.*;

@Entity
@Table(name="payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("id_payment")
    private Integer idPayment;
}
