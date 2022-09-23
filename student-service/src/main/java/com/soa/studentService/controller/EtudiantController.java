package com.soa.studentService.controller;

import com.soa.studentService.model.Etudiant;
import com.soa.studentService.service.EtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/etudiants")
@AllArgsConstructor
public class EtudiantController {
    private final EtudiantService etudiantService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Etudiant body){
        etudiantService.create(body);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Etudiant body,@PathVariable long id){
        etudiantService.update(body,id);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        etudiantService.delete(id);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(etudiantService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{cne}")
    public ResponseEntity<?> delete(@PathVariable String cne){
        return new ResponseEntity<>(etudiantService.findByCne(cne),HttpStatus.OK);
    }
}
