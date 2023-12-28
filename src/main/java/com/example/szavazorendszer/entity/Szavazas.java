package com.example.szavazorendszer.entity;

import com.example.szavazorendszer.enums.SzavazasTipus;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "szavazasok")
public class Szavazas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "szavazas")
    private List<Szavazat> szavazatok;

    public Szavazas() {
    }

    public Szavazas(Date idopont, String targy, SzavazasTipus tipus, String elnok) {
        this.idopont = idopont;
        this.targy = targy;
        this.tipus = tipus;
        this.elnok = elnok;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getIdopont() {
        return idopont;
    }

    public void setIdopont(Date idopont) {
        this.idopont = idopont;
    }

    public String getTargy() {
        return targy;
    }

    public void setTargy(String targy) {
        this.targy = targy;
    }

    public SzavazasTipus getTipus() {
        return tipus;
    }

    public void setTipus(SzavazasTipus tipus) {
        this.tipus = tipus;
    }

    public String getElnok() {
        return elnok;
    }

    public void setElnok(String elnok) {
        this.elnok = elnok;
    }

    public List<Szavazat> getSzavazatok() {
        return szavazatok;
    }

    public void setSzavazatok(List<Szavazat> szavazatok) {
        this.szavazatok = szavazatok;
    }
}
