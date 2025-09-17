package com.clinica.backend.service;

import com.clinica.backend.dto.Doctor;
import com.clinica.backend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public List<Doctor> doctorList(){
        return repository.findAll();
    }
    public Doctor save(Doctor doctor){
        return repository.save(doctor);
    }
    public void delete(Doctor doctor) {  repository.delete(doctor); }
    public Optional<Doctor> findById(Long id) {
        return repository.findById(id);
    }


}
