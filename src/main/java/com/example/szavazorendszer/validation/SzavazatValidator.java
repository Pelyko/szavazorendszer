package com.example.szavazorendszer.validation;

import com.example.szavazorendszer.dto.SzavazatDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;


@Component
public class SzavazatValidator {
    public void validate(SzavazatDTO szavazat) throws ValidationException {
        if(szavazat.getKepviselo() == null){
            throw new ValidationException("A képviselőt kötelező megadni.");
        }
        if(szavazat.getErtek() == null){
            throw new ValidationException("A szavazás értékét kötelező megadni.");
        }

    }
}
