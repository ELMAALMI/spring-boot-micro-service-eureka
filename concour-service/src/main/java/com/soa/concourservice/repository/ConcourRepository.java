package com.soa.concourservice.repository;

import com.soa.concourservice.model.Concour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ConcourRepository extends JpaRepository<Concour,Long> {
    Optional<Concour> findByNom(String nom);
}
