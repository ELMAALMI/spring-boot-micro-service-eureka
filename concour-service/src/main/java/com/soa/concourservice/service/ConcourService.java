package com.soa.concourservice.service;


import com.soa.concourservice.model.Condidate;
import com.soa.concourservice.model.Concour;

import java.util.List;

public interface ConcourService {
    void create(Concour councour);
    void update(Concour councour, long id);
    void delete(long id);
    List<Concour> findAll();
    void addCondidate(Condidate condidate,long councour);
}
