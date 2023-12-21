package com.example.szavazorendszer.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

enum SzavazasTipus {
    J("j"),
    E("e"),
    M("m");

    public final String value;

    private SzavazasTipus(String value){
        this.value = value;
    }
}

@Entity
@Table(name = "szavazasok")
public class Szavazas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "idopont", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date idopont;

    @Column(name = "targy", nullable = false)
    private String targy;

    @Column(name = "tipus", nullable = false)
    private SzavazasTipus tipus;

    @Column(name = "elnok", nullable = false)
    private String elnok;

    @OneToMany(mappedBy = "szavazas")
    private List<Szavazat> szavazatok;
}
