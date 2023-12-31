package com.example.szavazorendszer.dto;

import com.example.szavazorendszer.enums.SzavazasTipus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SzavazasDTO {
    @NotNull(message = "A szavazás időpontját meg kell adni!")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date idopont;

    @NotEmpty(message = "A tárgyat meg kell adni!")
    private String targy;

    @NotNull(message = "A szavazás típusát meg kell adni!")
    private SzavazasTipus tipus;

    @NotEmpty(message = "A szavazás elnökét meg kell adni!")
    private String elnok;

    @NotEmpty(message = "Meg kell adni legalább 1 szavazatot.")
    private List<SzavazatDTO> szavazatok;

    public SzavazasDTO(Date idopont, String targy, SzavazasTipus tipus, String elnok, List<SzavazatDTO> szavazatok) throws ParseException {
        this.idopont = idopont;
        this.targy = targy;
        this.tipus = tipus;
        this.elnok = elnok;
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

    public List<SzavazatDTO> getSzavazatok() {
        return szavazatok;
    }

    public void setSzavazatok(List<SzavazatDTO> szavazatok) {
        this.szavazatok = szavazatok;
    }
}
