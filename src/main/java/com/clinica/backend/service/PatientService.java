package com.clinica.backend.service;

import com.clinica.backend.dto.Patient;
import com.clinica.backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public Page<Patient> patientList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Patient save(Patient patient) {
        return repository.save(patient);
    }

    public void delete(Patient patient) {
        repository.delete(patient);
    }

    public Optional<Patient> findById(Long id) {
        return repository.findById(id);
    }

}
