package com.clinica.backend.service;

import com.clinica.backend.dto.Doctor;
import com.clinica.backend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public Page<Doctor> doctorList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Doctor save(Doctor doctor) {
        return repository.save(doctor);
    }

    public void delete(Doctor doctor) {
        repository.delete(doctor);
    }

    public Optional<Doctor> findById(Long id) {
        return repository.findById(id);
    }


}
