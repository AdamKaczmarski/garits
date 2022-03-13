package com.garits.vehicle;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reg_no")
    private String idRegNo;
}
