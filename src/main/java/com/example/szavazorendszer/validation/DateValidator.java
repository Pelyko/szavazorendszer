package com.example.szavazorendszer.validation;

import javax.xml.bind.ValidationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {
    private String dateFormat;

    public DateValidator(String dateFormat){
        this.dateFormat = dateFormat;
    }

    public Date validate(String date) throws ValidationException {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new ValidationException("Nem megfelelő dátum formátum a kérésben!");
        }
    }
}
