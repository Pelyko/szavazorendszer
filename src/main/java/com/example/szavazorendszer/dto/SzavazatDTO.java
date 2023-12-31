package com.example.szavazorendszer.dto;

import com.example.szavazorendszer.SzavazorendszerApplication;
import com.example.szavazorendszer.enums.SzavazatErtek;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SzavazatDTO {

    @NotNull(message = "A képviselő nevét meg kell adni.")
    @NotEmpty(message = "A képviselő nevét meg kell adni.")
    private String kepviselo;

    @NotNull(message = "A képviselő nevét meg kell adni.")
    @NotEmpty(message = "A képviselő nevét meg kell adni.")
    private SzavazatErtek szavazat;

    public SzavazatDTO(String kepviselo, SzavazatErtek szavazat) {
        this.kepviselo = kepviselo;
        this.szavazat = szavazat;
    }

    public String getKepviselo() {
        return kepviselo;
    }

    public void setKepviselo(String kepviselo) {
        this.kepviselo = kepviselo;
    }

    public SzavazatErtek getErtek() {
        return szavazat;
    }

    public void setErtek(SzavazatErtek szavazat) {
        this.szavazat = szavazat;
    }
}
