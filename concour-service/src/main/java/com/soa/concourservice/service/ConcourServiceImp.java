package com.soa.concourservice.service;

import com.soa.concourservice.dto.EtudiantResponse;
import com.soa.concourservice.exception.BadRequestException;
import com.soa.concourservice.exception.NotFoundException;
import com.soa.concourservice.model.Condidate;
import com.soa.concourservice.model.Concour;
import com.soa.concourservice.repository.ConcourRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConcourServiceImp implements ConcourService {
    private final ConcourRepository councourRepository;
    private final WebClient.Builder webClient;
    @Override
    public void create(Concour councour) {
        Optional<Concour> optional = councourRepository.findByNom(councour.getNom());
        if(optional.isPresent()){
            throw new BadRequestException("Councour Name already taken");
        }
        councourRepository.save(councour);
    }

    @Override
    public void update(Concour councour, long id) {
        Concour update_councour = this.findById(id);
        BeanUtils.copyProperties(councour,update_councour);
        update_councour.setId(id);
        councourRepository.save(update_councour);
    }

    @Override
    public void delete(long id) {
        Concour councour = this.findById(id);
        councourRepository.delete(councour);
    }

    @Override
    public List<Concour> findAll() {
        return councourRepository.findAll();
    }

    @Override
    public void addCondidate(Condidate condidate, long councourId) {
        try{
            EtudiantResponse response = webClient.build()
                                            .get()
                                            .uri("http://student-service/etudiants/"+condidate.getCne())
                                            .retrieve()
                                            .bodyToMono(EtudiantResponse.class)
                                            .block();
            Optional<Concour> councour = councourRepository.findById(councourId);
            if(councour.isEmpty()){
                throw new NotFoundException("councour not found");
            }
            boolean already_sub = councour.get().getCondidates().stream().anyMatch((c)->c.getCne().equals(condidate.getCne()));
            if (already_sub){
                throw new BadRequestException("condidate already registed");
            }
            if(
                    response.getNote_anglais() > councour.get().getMin_note()
                    && response.getNote_arabe() > councour.get().getMin_note()
                    && response.getNote_math() > councour.get().getMin_note()
            ){
                councour.get().getCondidates().add(condidate);
                councourRepository.save(councour.get());
            }else {
                throw new BadRequestException("condidate don't have 12 >");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    private Concour findById(long id){
        Optional<Concour> councour = councourRepository.findById(id);
        return councour.orElseThrow(()->new NotFoundException("councour not found"));
    }
}
