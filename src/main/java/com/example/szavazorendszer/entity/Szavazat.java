package com.example.szavazorendszer.entity;

import jakarta.persistence.*;

enum SzavazatErtek {
    I("i"),
    N("n"),
    T("t");

    public final String value;

    private SzavazatErtek(String value){
        this.value = value;
    }
}
@Entity
@Table(name = "szavazatok")
public class Szavazat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "kepviselo", nullable = false)
    private String kepviselo;

    @Column(name = "szavazat_ertek", nullable = false)
    private SzavazatErtek szavazatErtek;

    @ManyToOne
    private Szavazas szavazas;
}
