package com.example.szavazorendszer.entity;

import com.example.szavazorendszer.enums.SzavazatErtek;
import jakarta.persistence.*;


@Entity
@Table(name = "szavazatok")
public class Szavazat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kepviselo", nullable = false)
    private String kepviselo;

    @Column(name = "szavazat_ertek", nullable = false)
    private SzavazatErtek szavazatErtek;

    @ManyToOne
    private Szavazas szavazas;

    public Szavazat() {
    }

    public Szavazat(String kepviselo, SzavazatErtek szavazatErtek, Szavazas szavazas) {
        this.kepviselo = kepviselo;
        this.szavazatErtek = szavazatErtek;
        this.szavazas = szavazas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKepviselo() {
        return kepviselo;
    }

    public void setKepviselo(String kepviselo) {
        this.kepviselo = kepviselo;
    }

    public SzavazatErtek getSzavazatErtek() {
        return szavazatErtek;
    }

    public void setSzavazatErtek(SzavazatErtek szavazatErtek) {
        this.szavazatErtek = szavazatErtek;
    }

    public Szavazas getSzavazas() {
        return szavazas;
    }

    public void setSzavazas(Szavazas szavazas) {
        this.szavazas = szavazas;
    }
}
