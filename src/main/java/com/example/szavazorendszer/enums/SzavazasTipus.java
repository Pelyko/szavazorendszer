package com.example.szavazorendszer.enums;

public enum SzavazasTipus {
    j("j"),
    e("e"),
    m("m");

    public final String value;

    private SzavazasTipus(String value){
        this.value = value;
    }
}
