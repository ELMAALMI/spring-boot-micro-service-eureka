package com.soa.studentService.repository;


import com.soa.studentService.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    Optional<Etudiant> findByCinOrCne(String cin, String cne);
    Optional<Etudiant> findByCne(String cne);
}
