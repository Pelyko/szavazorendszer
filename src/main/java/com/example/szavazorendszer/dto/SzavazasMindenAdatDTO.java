package com.example.szavazorendszer.dto;

import com.example.szavazorendszer.entity.Szavazat;
import com.example.szavazorendszer.enums.SzavazasEredmeny;
import com.example.szavazorendszer.enums.SzavazasTipus;

import java.util.Date;
import java.util.List;

public class SzavazasMindenAdatDTO {
    private Date idopont;

    private String targy;

    private SzavazasTipus tipus;

    private String elnok;

    private SzavazasEredmeny eredmeny;

    private int kepviselokSzama;
    private List<SzavazatDTO> szavazatok;


    public SzavazasMindenAdatDTO(Date idopont, String targy, SzavazasTipus tipus, String elnok, SzavazasEredmeny eredmeny, int kepviselokSzama, List<SzavazatDTO> szavazatok) {
        this.idopont = idopont;
        this.targy = targy;
        this.tipus = tipus;
        this.elnok = elnok;
        this.eredmeny = eredmeny;
        this.kepviselokSzama = kepviselokSzama;
        this.szavazatok = szavazatok;
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

    public SzavazasEredmeny getEredmeny() {
        return eredmeny;
    }

    public void setEredmeny(SzavazasEredmeny eredmeny) {
        this.eredmeny = eredmeny;
    }

    public int getKepviselokSzama() {
        return kepviselokSzama;
    }

    public void setKepviselokSzama(int kepviselokSzama) {
        this.kepviselokSzama = kepviselokSzama;
    }

    public List<SzavazatDTO> getSzavazatok() {
        return szavazatok;
    }

    public void setSzavazatok(List<SzavazatDTO> szavazatok) {
        this.szavazatok = szavazatok;
    }
}
