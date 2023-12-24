package com.example.szavazorendszer.repository;

import com.example.szavazorendszer.entity.Szavazas;
import com.example.szavazorendszer.enums.SzavazasTipus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SzavazasRepository extends JpaRepository<Szavazas,Long> {

    @Query("SELECT szavazas FROM Szavazas szavazas JOIN Szavazat szavazat ON szavazat.szavazas = szavazas WHERE szavazas.idopont < ?1 AND szavazas.tipus = ?2 ORDER BY szavazas.idopont DESC LIMIT 1")
    List<Szavazas> findLastPresentElectionBeforeDateByType(Date idopont, SzavazasTipus tipus);

    @Query("SELECT szavazas FROM Szavazas szavazas JOIN Szavazat szavazat ON szavazat.szavazas = szavazas WHERE szavazas.id = ?1")
    List<Szavazas> findElectionById(Long szavazas);
}
