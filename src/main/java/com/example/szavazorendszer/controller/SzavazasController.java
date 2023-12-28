package com.example.szavazorendszer.controller;

import com.example.szavazorendszer.dto.SzavazasAdatokDTO;
import com.example.szavazorendszer.dto.SzavazasDTO;
import com.example.szavazorendszer.dto.SzavazasMindenAdatDTO;
import com.example.szavazorendszer.exception.ElectionNotFoundException;
import com.example.szavazorendszer.exception.VoteNotFoundException;
import com.example.szavazorendszer.service.SzavazasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.*;

@Controller
@RequestMapping(value = "/szavazasok")
public class SzavazasController{

    @Autowired
    private SzavazasService szavazasService;

    @PostMapping(value="/szavazas")
    public @ResponseBody ResponseEntity<?> election(@RequestBody @Valid SzavazasDTO szavazas) {
        Long id = szavazasService.registerElection(szavazas);
        Map<String,Long> response = new HashMap<>();
        response.put("szavazasId",id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value="/szavazat")
    public @ResponseBody ResponseEntity<?> vote(@RequestParam @Valid Long szavazas, @RequestParam String kepviselo) throws VoteNotFoundException {
        String szavazatErtek = szavazasService.findVote(szavazas,kepviselo);
        Map<String,String> response = new HashMap<>();
        response.put("szavazat",szavazatErtek);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value="/eredmeny")
    public @ResponseBody ResponseEntity<?> electionResult(@RequestParam @Valid Long szavazas) throws ElectionNotFoundException {
        SzavazasAdatokDTO szavazasAdatokDTO = szavazasService.getDataOfElection(szavazas);
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("eredmeny",szavazasAdatokDTO.getSzavazasEredmeny().value);
        response.put("kepviselokSzama",szavazasAdatokDTO.getKepviselokSzama());
        response.put("igenekSzama",szavazasAdatokDTO.getIgenekSzama());
        response.put("nemekSzama",szavazasAdatokDTO.getNemekSzama());
        response.put("tartozkodasokSzama",szavazasAdatokDTO.getTartozkodasokSzama());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping(value="/napi-szavazasok")
    public @ResponseBody ResponseEntity<?> dailyElections(@RequestParam @Valid String nap) throws ValidationException, ElectionNotFoundException {
        List<SzavazasMindenAdatDTO> szavazasok = szavazasService.getElectionsDataByDate(nap);
        Map<String,List<SzavazasMindenAdatDTO>> response = new HashMap<>();
        response.put("szavazasok", szavazasok);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
