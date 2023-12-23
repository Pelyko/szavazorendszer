package com.example.szavazorendszer.repository;

import com.example.szavazorendszer.entity.Szavazat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SzavazatRepository extends JpaRepository<Szavazat,Long> {
}
