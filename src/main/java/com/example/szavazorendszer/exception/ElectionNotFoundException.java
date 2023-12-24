package com.example.szavazorendszer.exception;

public class ElectionNotFoundException extends Exception{
    private String message;

    public ElectionNotFoundException(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
