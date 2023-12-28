package com.example.szavazorendszer.repository;

import com.example.szavazorendszer.entity.Szavazat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SzavazatRepository extends JpaRepository<Szavazat,Long> {

    @Query("SELECT szavazat FROM Szavazat szavazat JOIN Szavazas szavazas ON szavazat.szavazas = szavazas WHERE szavazas.id = :szavazas AND szavazat.kepviselo = :kepviselo")
    List<Szavazat> findBySzavazasAndKepviselo(@Param("szavazas") Long szavazas, @Param("kepviselo") String kepviselo);

}
