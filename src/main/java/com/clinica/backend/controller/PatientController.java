package com.clinica.backend.controller;

import com.clinica.backend.service.PatientService;
import com.clinica.backend.dto.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping
    public List<Patient> listAll() {
        return service.patientList();
    }

    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient patient){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientNew){
        return service.findById(id)
                 .map(patient ->  {
            patient.setName(patientNew.getName());
            patient.setEmail(patientNew.getEmail());
            Patient updated = service.save(patient);
            return ResponseEntity.ok(updated);
        })
                 .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        return service.findById(id)
                .map(pacient -> {
                    service.delete(pacient);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
