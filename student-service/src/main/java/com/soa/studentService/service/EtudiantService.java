package com.soa.studentService.service;

import com.soa.studentService.model.Etudiant;

import java.util.List;

public interface EtudiantService {
    void create(Etudiant etudiant);
    void update(Etudiant etudiant,long id);
    void delete(long id);
    List<Etudiant> findAll();
    Etudiant findByCne(String cne);
}
