package com.soa.studentService.service;


import com.soa.studentService.exception.BadRequestException;
import com.soa.studentService.exception.NotFoundException;
import com.soa.studentService.model.Etudiant;
import com.soa.studentService.repository.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EtudiantServiceImp implements EtudiantService{
    private final EtudiantRepository etudiantRepository;
    @Override
    public void create(Etudiant etudiant) {
        Optional<Etudiant> optional = etudiantRepository.findByCinOrCne(etudiant.getCin(), etudiant.getCne());
        if (optional.isPresent()){
            throw new BadRequestException("Cne or Cin already used");
        }
        etudiantRepository.save(etudiant);
    }

    @Override
    public void update(Etudiant etudiant, long id) {
        Etudiant student = this.findById(id);
        BeanUtils.copyProperties(etudiant,student);
        student.setId(id);
        etudiantRepository.save(student);
    }

    @Override
    public void delete(long id) {
        Etudiant student = this.findById(id);
        etudiantRepository.delete(student);
    }

    @Override
    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant findByCne(String cne) {
        Optional<Etudiant> etudiant = etudiantRepository.findByCne(cne);
        return etudiant.orElseThrow(()->new NotFoundException("Student with "+cne+" not found"));
    }
    private Etudiant findById(long id){
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        return etudiant.orElseThrow(()->new NotFoundException("student not found"));
    }

}
