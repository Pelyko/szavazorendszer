package com.example.szavazorendszer.enums;

public enum SzavazasEredmeny {
    ELUTASITOTT("U"),
    ELFOGADOTT("F");

    public String value;

    private SzavazasEredmeny(String value){
        this.value = value;
    }
}
