package com.clinica.backend.service;

import com.clinica.backend.dto.Consult;
import com.clinica.backend.repository.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultService {

    @Autowired
    private ConsultRepository repository;

    public Page<Consult> consultList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Consult save(Consult consult) {
        return repository.save(consult);
    }

    public void delete(Consult consult) {
        repository.delete(consult);
    }

    public Optional<Consult> findById(Long id) {
        return repository.findById(id);
    }


}
