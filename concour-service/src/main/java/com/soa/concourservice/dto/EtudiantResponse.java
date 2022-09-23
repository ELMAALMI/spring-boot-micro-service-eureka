package com.soa.concourservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantResponse {
    private long id;
    private String nom;
    private String prenom;
    private String cin;
    private String cne;
    private double note_math;
    private double note_arabe;
    private double note_anglais;
}
