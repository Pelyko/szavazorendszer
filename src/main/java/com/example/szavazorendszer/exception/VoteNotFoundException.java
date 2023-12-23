package com.example.szavazorendszer.exception;
public class VoteNotFoundException extends Exception{

    private final String message;

    public VoteNotFoundException(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
