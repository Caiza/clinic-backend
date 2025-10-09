package com.clinica.backend.controller;

import com.clinica.backend.exception.PatientNotFoundException;
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
        Patient updated = service.findById(id)
                 .map(patient ->  {
            patient.setName(patientNew.getName());
            patient.setEmail(patientNew.getEmail());
            return  service.save(patient);
        }).orElseThrow(() -> new PatientNotFoundException(id) );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
         service.findById(id)
                 .ifPresentOrElse(
                         patient -> service.delete(patient),
                         () -> { throw new PatientNotFoundException(id); }
                 );
         return ResponseEntity.noContent().build();
    }


}
