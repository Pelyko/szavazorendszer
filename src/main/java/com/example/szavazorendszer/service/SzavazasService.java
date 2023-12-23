package com.example.szavazorendszer.service;

import com.example.szavazorendszer.repository.SzavazasRepository;
import com.example.szavazorendszer.dto.SzavazasDTO;
import com.example.szavazorendszer.entity.Szavazas;
import com.example.szavazorendszer.entity.Szavazat;
import com.example.szavazorendszer.repository.SzavazatRepository;
import com.example.szavazorendszer.validation.SzavazasValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SzavazasService {

    @Autowired
    private SzavazasValidator szavazasValidator;
    @Autowired
    private SzavazasRepository szavazasRepository;
    @Autowired
    private SzavazatRepository szavazatRepository;
    public Long registerElection(SzavazasDTO szavazas) {
        szavazasValidator.validate(szavazas);

        Szavazas szavazasEntity = new Szavazas(
                szavazas.getIdopont(),
                szavazas.getTargy(),
                szavazas.getTipus(),
                szavazas.getElnok()
        );
        Long id = szavazasRepository.save(szavazasEntity).getId();

        szavazas.getSzavazatok().forEach(
                sz -> {
                    Szavazat szavazatEntity = new Szavazat(
                            sz.getKepviselo(),
                            sz.getErtek(),
                            szavazasEntity
                    );
                    szavazatRepository.save(szavazatEntity);
                }
        );

        return id;
    }
}
