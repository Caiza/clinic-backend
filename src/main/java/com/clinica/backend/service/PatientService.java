package com.clinica.backend.service;

import com.clinica.backend.repository.PatientRepository;
import com.clinica.backend.dto.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public List<Patient> patientList(){
        return repository.findAll();
    }
    public Patient save(Patient patient){
        return repository.save(patient);
    }
    public void delete(Patient patient) {  repository.delete(patient); }
    public Optional<Patient> findById(Long id) {
        return repository.findById(id);
    }


}
