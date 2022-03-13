package com.garits.service;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service")
    private Integer idService;
}
