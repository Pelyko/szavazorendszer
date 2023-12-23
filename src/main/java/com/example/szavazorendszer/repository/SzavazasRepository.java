package com.example.szavazorendszer.repository;

import com.example.szavazorendszer.entity.Szavazas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SzavazasRepository extends JpaRepository<Szavazas,Long> {
}
