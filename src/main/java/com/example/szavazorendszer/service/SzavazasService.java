package com.example.szavazorendszer.service;

import com.example.szavazorendszer.exception.VoteNotFoundException;
import com.example.szavazorendszer.repository.SzavazasRepository;
import com.example.szavazorendszer.dto.SzavazasDTO;
import com.example.szavazorendszer.entity.Szavazas;
import com.example.szavazorendszer.entity.Szavazat;
import com.example.szavazorendszer.repository.SzavazatRepository;
import com.example.szavazorendszer.validation.SzavazasValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String findVote(Long szavazas, String kepviselo) throws VoteNotFoundException {
        List<Szavazat> szavazatok = szavazatRepository.findBySzavazasAndKepviselo(szavazas,kepviselo);
        if(szavazatok.isEmpty()){
            throw new VoteNotFoundException("A keresett szavazat nem található.");
        }
        return szavazatok.get(0).getSzavazatErtek().value;
    }
}
