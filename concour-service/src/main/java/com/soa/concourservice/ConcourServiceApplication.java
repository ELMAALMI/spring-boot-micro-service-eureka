package com.soa.concourservice;

import com.soa.concourservice.model.Concour;
import com.soa.concourservice.repository.ConcourRepository;
import com.soa.concourservice.service.ConcourService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Optional;

@SpringBootApplication
@AllArgsConstructor
@EnableEurekaClient
public class ConcourServiceApplication implements CommandLineRunner {
    private final ConcourRepository concourRepository;

    public static void main(String[] args) {
        SpringApplication.run(ConcourServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Concour concour = Concour.builder()
                .nom("EST")
                .min_note(12)
                .build();
        Concour concour2 = Concour.builder()
                .nom("ENSA")
                .min_note(14)
                .build();
        Optional<Concour> optional = concourRepository.findByNom(concour.getNom());
        if(optional.isEmpty()){
            concourRepository.save(concour);
            concourRepository.save(concour2);
        }
    }
}
