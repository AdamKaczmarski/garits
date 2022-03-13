package com.garits.part;

import javax.persistence.*;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_part")
    private Integer idPart;
}
