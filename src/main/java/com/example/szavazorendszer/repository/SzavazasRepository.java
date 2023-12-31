package com.example.szavazorendszer.repository;

import com.example.szavazorendszer.entity.Szavazas;
import com.example.szavazorendszer.enums.SzavazasTipus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SzavazasRepository extends JpaRepository<Szavazas,Long> {

    @Query("SELECT szavazas FROM Szavazas szavazas JOIN Szavazat szavazat ON szavazat.szavazas = szavazas WHERE szavazas.idopont < :idopont AND szavazas.tipus = :tipus ORDER BY szavazas.idopont DESC LIMIT 1")
    List<Szavazas> findLastPresentElectionBeforeDateByType(@Param("idopont") Date idopont, @Param("tipus") SzavazasTipus tipus);

    @Query("SELECT szavazas FROM Szavazas szavazas JOIN Szavazat szavazat ON szavazat.szavazas = szavazas WHERE szavazas.id = :szavazas")
    List<Szavazas> findElectionById(@Param("szavazas") Long szavazas);

    @Query("SELECT szavazas FROM Szavazas szavazas JOIN Szavazat szavazat ON szavazat.szavazas = szavazas WHERE datediff(day,:idopont,szavazas.idopont) = 0")
    List<Szavazas> findElectionByDate(@Param("idopont") Date idopont);

    @Query("SELECT COUNT(szavazas) FROM Szavazas szavazas JOIN Szavazat szavazat ON szavazat.szavazas = szavazas WHERE szavazas.idopont BETWEEN :kezdoDatum AND :vegDatum AND szavazas.tipus != SzavazasTipus.j GROUP BY szavazat.kepviselo")
    List<Integer> getNumberOfElectionsByRepresentativesBetweenDates(@Param("kezdoDatum") Date kezdoDatum, @Param("vegDatum") Date vegDatum);
}
