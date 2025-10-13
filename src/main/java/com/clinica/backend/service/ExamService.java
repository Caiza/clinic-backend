package com.clinica.backend.service;

import com.clinica.backend.dto.Exam;
import com.clinica.backend.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository repository;

    public Page<Exam> examList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Exam save(Exam exam) {
        return repository.save(exam);
    }

    public void delete(Exam exam) {
        repository.delete(exam);
    }

    public Optional<Exam> findById(Long id) {
        return repository.findById(id);
    }


}
