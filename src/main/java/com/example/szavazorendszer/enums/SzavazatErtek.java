package com.example.szavazorendszer.enums;

public enum SzavazatErtek {
    i("i"),
    n("n"),
    t("t");

    public final String value;

    private SzavazatErtek(String value){
        this.value = value;
    }
}
