package com.soa.concourservice.controller;

import com.soa.concourservice.model.Concour;
import com.soa.concourservice.model.Condidate;
import com.soa.concourservice.service.ConcourService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/concours")
@AllArgsConstructor
public class ConcourController {
    private final ConcourService concourService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Concour concour){
        concourService.create(concour);
        System.out.println("from :: => "+concour);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Concour concour,@PathVariable long id){
        concourService.update(concour,id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        concourService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> all(){
        return new ResponseEntity<>(concourService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/condidates/{concourId}")
    public ResponseEntity<?> saveCondidate(@RequestBody Condidate condidate,@PathVariable long concourId){
        concourService.addCondidate(condidate,concourId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
