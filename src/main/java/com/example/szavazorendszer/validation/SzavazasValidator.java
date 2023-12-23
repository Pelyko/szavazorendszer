package com.example.szavazorendszer.validation;

import com.example.szavazorendszer.dto.SzavazasDTO;
import com.example.szavazorendszer.dto.SzavazatDTO;
import com.example.szavazorendszer.entity.Szavazas;
import com.example.szavazorendszer.repository.SzavazasRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SzavazasValidator {

    @Autowired
    SzavazasRepository szavazasRepository;
    public void validate(SzavazasDTO szavazas) throws ValidationException {

        if(szavazas.getSzavazatok().stream().noneMatch(sz -> sz.getKepviselo().equals(szavazas.getElnok()))){
            throw new ValidationException("Az elnöknek is szavaznia kell!");
        }

        if(szavazas.getSzavazatok().stream()
                .map(SzavazatDTO::getKepviselo)
                .distinct()
                .count() != szavazas.getSzavazatok().size()) {
            throw new ValidationException("Egy képviselő csak egyszer szavazhat!");
        }

        if(szavazasRepository.findAll().stream().map(Szavazas::getIdopont).anyMatch(sz -> sz.compareTo(szavazas.getIdopont()) == 0)){
            throw new ValidationException("Ebben az időben már történt szavazás!");
        }
    }
}
