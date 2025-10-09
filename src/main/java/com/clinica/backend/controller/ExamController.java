package com.clinica.backend.controller;

import com.clinica.backend.dto.Exam;
import com.clinica.backend.exception.ExamNotFoundException;
import com.clinica.backend.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamController {

    @Autowired
    private ExamService service;

    @GetMapping
    public List<Exam> listAll() {
        return service.examList();
    }

    @PostMapping
    public ResponseEntity<Exam> create(@RequestBody Exam exam){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(exam));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam examNew){
        Exam updated = service.findById(id)
                 .map(exam ->  {
                     exam.setName(examNew.getName());
                     return service.save(exam);
        }).orElseThrow(() -> new ExamNotFoundException(id));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        service.findById(id)
                .ifPresentOrElse(
                        exam -> service.delete(exam),
                        () -> { throw new ExamNotFoundException(id);}
                );
        return ResponseEntity.noContent().build();
    }


}
