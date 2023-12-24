package com.example.szavazorendszer.dto;

import com.example.szavazorendszer.enums.SzavazasEredmeny;

public class SzavazasAdatokDTO {
    private SzavazasEredmeny szavazasEredmeny;
    private int kepviselokSzama;
    private int igenekSzama;
    private int nemekSzama;
    private int tartozkodasokSzama;

    public SzavazasAdatokDTO() {
    }

    public SzavazasAdatokDTO(SzavazasEredmeny szavazasEredmeny, int kepviselokSzama, int igenekSzama, int nemekSzama, int tartozkodasokSzama) {
        this.szavazasEredmeny = szavazasEredmeny;
        this.kepviselokSzama = kepviselokSzama;
        this.igenekSzama = igenekSzama;
        this.nemekSzama = nemekSzama;
        this.tartozkodasokSzama = tartozkodasokSzama;
    }

    public SzavazasEredmeny getSzavazasEredmeny() {
        return szavazasEredmeny;
    }

    public void setSzavazasEredmeny(SzavazasEredmeny szavazasEredmeny) {
        this.szavazasEredmeny = szavazasEredmeny;
    }

    public int getKepviselokSzama() {
        return kepviselokSzama;
    }

    public void setKepviselokSzama(int kepviselokSzama) {
        this.kepviselokSzama = kepviselokSzama;
    }

    public int getIgenekSzama() {
        return igenekSzama;
    }

    public void setIgenekSzama(int igenekSzama) {
        this.igenekSzama = igenekSzama;
    }

    public int getNemekSzama() {
        return nemekSzama;
    }

    public void setNemekSzama(int nemekSzama) {
        this.nemekSzama = nemekSzama;
    }

    public int getTartozkodasokSzama() {
        return tartozkodasokSzama;
    }

    public void setTartozkodasokSzama(int tartozkodasokSzama) {
        this.tartozkodasokSzama = tartozkodasokSzama;
    }
}
