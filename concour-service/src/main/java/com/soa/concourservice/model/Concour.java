package com.soa.concourservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@ToString
public class Concour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //0649782924
    @Column
    private String nom;
    @Column
    private double min_note;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "councours_condidates",
            joinColumns = @JoinColumn(name = "councour_id"),
            inverseJoinColumns = @JoinColumn(name = "condidate_id")
    )
    Set<Condidate> condidates;
}
