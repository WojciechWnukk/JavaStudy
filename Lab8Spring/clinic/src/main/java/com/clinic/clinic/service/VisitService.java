package com.clinic.clinic.service;

import com.clinic.clinic.model.Visit;
import com.clinic.clinic.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public Visit getVisitById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    public Visit addVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    public Visit updateVisit(Visit visit) {
        Visit existingVisit = visitRepository.findById(visit.getId()).orElse(null);
        existingVisit.setDate(visit.getDate());
        return visitRepository.save(existingVisit);
    }

    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }
}
