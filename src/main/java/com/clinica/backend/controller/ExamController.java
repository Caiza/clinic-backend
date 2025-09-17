package com.clinica.backend.controller;

import com.clinica.backend.dto.Exam;
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
    public ResponseEntity<Exam> updatePatient(@PathVariable Long id, @RequestBody Exam examNew){
        return service.findById(id)
                 .map(exam ->  {
                     exam.setName(examNew.getName());
                     Exam updated = service.save(exam);
            return ResponseEntity.ok(updated);
        })
                 .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        return service.findById(id)
                .map(exam -> {
                    service.delete(exam);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
