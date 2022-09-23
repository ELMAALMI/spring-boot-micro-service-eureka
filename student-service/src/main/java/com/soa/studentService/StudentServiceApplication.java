package com.soa.studentService;

import com.soa.studentService.model.Etudiant;
import com.soa.studentService.repository.EtudiantRepository;
import com.soa.studentService.service.EtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Optional;

@SpringBootApplication
@AllArgsConstructor
@EnableEurekaClient
public class StudentServiceApplication implements CommandLineRunner {
    private final EtudiantRepository etudiantRepository;
    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Etudiant etudiant = Etudiant.builder()
                .cin("D877128")
                .cne("M1312154655")
                .nom("EL MAALMI")
                .prenom("BILLAL")
                .note_anglais(12)
                .note_arabe(13)
                .note_math(19)
                .build();
        Etudiant etudiant_1 = Etudiant.builder()
                .cin("E787127")
                .cne("M18898978")
                .nom("BAALO")
                .prenom("REDA")
                .note_anglais(15)
                .note_arabe(11)
                .note_math(19)
                .build();
        Optional<Etudiant> optional = etudiantRepository.findByCne(etudiant.getCne());
        if(optional.isEmpty()){
            etudiantRepository.save(etudiant);
            etudiantRepository.save(etudiant_1);
        }
    }
}
