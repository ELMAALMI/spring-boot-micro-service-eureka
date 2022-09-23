package com.soa.studentService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //0649782924
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column(unique = true)
    private String cin;
    @Column(unique = true)
    private String cne;
    @Column
    private double note_math;
    @Column
    private double note_arabe;
    @Column
    private double note_anglais;
}
