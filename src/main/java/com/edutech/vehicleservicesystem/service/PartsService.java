package com.edutech.vehicleservicesystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.vehicleservicesystem.entity.Parts;
import com.edutech.vehicleservicesystem.repository.PartsRepository;

@Service
public class PartsService {

    @Autowired
    private PartsRepository partsRepository;

    public Parts addPart(Parts part) {
        return partsRepository.save(part);
    }

    public List<Parts> getAllParts() {
        return partsRepository.findAll();
    }

    public Optional<Parts> getPartById(Long id) {
        return partsRepository.findById(id);
    }

    public Parts updatePart(Parts part) {
        return partsRepository.save(part);
    }

    public void deletePart(Long id) {
        partsRepository.deleteById(id);
    }
}