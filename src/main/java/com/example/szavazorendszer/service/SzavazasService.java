package com.example.szavazorendszer.service;

import com.example.szavazorendszer.dto.SzavazasAdatokDTO;
import com.example.szavazorendszer.dto.SzavazasMindenAdatDTO;
import com.example.szavazorendszer.dto.SzavazatDTO;
import com.example.szavazorendszer.enums.SzavazasEredmeny;
import com.example.szavazorendszer.enums.SzavazasTipus;
import com.example.szavazorendszer.enums.SzavazatErtek;
import com.example.szavazorendszer.exception.ElectionNotFoundException;
import com.example.szavazorendszer.exception.VoteNotFoundException;
import com.example.szavazorendszer.repository.SzavazasRepository;
import com.example.szavazorendszer.dto.SzavazasDTO;
import com.example.szavazorendszer.entity.Szavazas;
import com.example.szavazorendszer.entity.Szavazat;
import com.example.szavazorendszer.repository.SzavazatRepository;
import com.example.szavazorendszer.validation.DateValidator;
import com.example.szavazorendszer.validation.SzavazasValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;

@Service
public class SzavazasService {

    private static final int OSSZ_KEPVISELO = 200;
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

    public SzavazasAdatokDTO getDataOfElection(Long szavazas) throws ElectionNotFoundException {
        List<Szavazas> szavazasok = szavazasRepository.findElectionById(szavazas);
        if(szavazasok.isEmpty()){
            throw new ElectionNotFoundException("Nem található a megadott ID-val szavazás.");
        }

        SzavazasAdatokDTO szavazasAdatokDTO = new SzavazasAdatokDTO();
        szavazasAdatokDTO.setSzavazasEredmeny(evalElection(szavazasok.get(0)));

        switch(szavazasok.get(0).getTipus()){
            case e:
                szavazasAdatokDTO.setKepviselokSzama(getNumberOfPresentRepresentatives(szavazasok.get(0)));
                break;
            case m:
                szavazasAdatokDTO.setKepviselokSzama(OSSZ_KEPVISELO);
                break;
            default:
                szavazasAdatokDTO.setKepviselokSzama(szavazasok.get(0).getSzavazatok().size());
        }

        szavazasAdatokDTO.setIgenekSzama((int) szavazasok.get(0).getSzavazatok().stream().filter(sz -> sz.getSzavazatErtek().equals(SzavazatErtek.i)).count());
        szavazasAdatokDTO.setNemekSzama((int) szavazasok.get(0).getSzavazatok().stream().filter(sz -> sz.getSzavazatErtek().equals(SzavazatErtek.n)).count());
        szavazasAdatokDTO.setTartozkodasokSzama((int) szavazasok.get(0).getSzavazatok().stream().filter(sz -> sz.getSzavazatErtek().equals(SzavazatErtek.t)).count());

        return szavazasAdatokDTO;
    }

    private SzavazasEredmeny evalElection(Szavazas szavazas) throws ElectionNotFoundException {

        if(szavazas.getTipus().equals(SzavazasTipus.j)){
            return SzavazasEredmeny.ELFOGADOTT;
        }

        if(szavazas.getTipus().equals(SzavazasTipus.m)){
            return szavazas.getSzavazatok().stream()
                    .filter(sz -> sz.getSzavazatErtek().equals(SzavazatErtek.i))
                    .count() > OSSZ_KEPVISELO / 2
                    ? SzavazasEredmeny.ELFOGADOTT
                    : SzavazasEredmeny.ELUTASITOTT;
        }

        //A feladat nem tér ki arra az esetre, ha nem volt még jelenléti szavazás. Úgy döntöttem, hogy ilyenkor Exception dobódik.
        return szavazas.getSzavazatok().stream()
                .filter(sz -> sz.getSzavazatErtek().equals(SzavazatErtek.i))
                .count() > getNumberOfPresentRepresentatives(szavazas) / 2
                ? SzavazasEredmeny.ELFOGADOTT
                : SzavazasEredmeny.ELUTASITOTT;
    }

    private int getNumberOfPresentRepresentatives(Szavazas szavazas) throws ElectionNotFoundException {
        List<Szavazas> utolsoJelenletiSzavazas = szavazasRepository.findLastPresentElectionBeforeDateByType(szavazas.getIdopont(),SzavazasTipus.j);
        if(utolsoJelenletiSzavazas.isEmpty()){
            throw new ElectionNotFoundException("Nem található a szavazást megelőző jelenléti szavazás.");
        }
        return utolsoJelenletiSzavazas.get(0).getSzavazatok().size();
    }

    public List<SzavazasMindenAdatDTO> getElectionsDataByDate(String dateString) throws ValidationException,ElectionNotFoundException {
        DateValidator dateValidator = new DateValidator("yyyy-MM-dd");
        Date date = dateValidator.validate(dateString);
        List<Szavazas> szavazasok = szavazasRepository.findElectionByDate(date);
        return mapSzavazasEntityToDTO(szavazasok);
    }

    private List<SzavazasMindenAdatDTO> mapSzavazasEntityToDTO(List<Szavazas> szavazasok) throws ElectionNotFoundException {
        List<SzavazasMindenAdatDTO> szavazasokDto = new ArrayList<>();
        for (Szavazas sz : szavazasok) {
            SzavazasMindenAdatDTO szavazasDto = new SzavazasMindenAdatDTO(
                    sz.getIdopont(),
                    sz.getTargy(),
                    sz.getTipus(),
                    sz.getElnok(),
                    evalElection(sz),
                    getNumberOfPresentRepresentatives(sz),
                    mapSzavazatEntityToDto(sz.getSzavazatok())
            );
            szavazasokDto.add(szavazasDto);
        }
        return szavazasokDto;
    }

    private List<SzavazatDTO> mapSzavazatEntityToDto(List<Szavazat> szavazatok){
        List<SzavazatDTO> szavazatokDto = new ArrayList<>();
        szavazatok.forEach(sz -> {
            SzavazatDTO szavazatDto = new SzavazatDTO(
                    sz.getKepviselo(),
                    sz.getSzavazatErtek()
            );
            szavazatokDto.add(szavazatDto);
        });
        return szavazatokDto;
    }

    //Nem volt megadva milyen formátumban várja a kérés a dátumot, így én a korábban használt ISO-8061-et vettem alapul.
    public double getAverageNumberOfElectionsByRepresentatives(String kezdoDatumStr, String vegDatumStr) {
        DateValidator dateValidator = new DateValidator("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date kezdoDatum = dateValidator.validate(kezdoDatumStr);
        Date vegDatum = dateValidator.validate(vegDatumStr);
        List<Integer> kepviselok = szavazasRepository.getNumberOfElectionsByRepresentativesBetweenDates(kezdoDatum,vegDatum);
        return kepviselok.stream().mapToInt(Integer::intValue).summaryStatistics().getAverage();
    }
}
