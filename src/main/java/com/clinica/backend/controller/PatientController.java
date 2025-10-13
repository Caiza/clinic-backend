package com.clinica.backend.controller;

import com.clinica.backend.dto.Patient;
import com.clinica.backend.exception.PatientNotFoundException;
import com.clinica.backend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping
    public ResponseEntity<Page<Patient>> listAll(Pageable pageable) {
        Page<Patient> result = service.patientList(pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient patient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientNew) {
        Patient updated = service.findById(id)
                .map(patient -> {
                    patient.setName(patientNew.getName());
                    patient.setEmail(patientNew.getEmail());
                    return service.save(patient);
                }).orElseThrow(() -> new PatientNotFoundException(id));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        service.findById(id)
                .ifPresentOrElse(
                        patient -> service.delete(patient),
                        () -> {
                            throw new PatientNotFoundException(id);
                        }
                );
        return ResponseEntity.noContent().build();
    }


}
