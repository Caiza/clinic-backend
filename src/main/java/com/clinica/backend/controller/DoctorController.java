package com.clinica.backend.controller;

import com.clinica.backend.dto.Doctor;
import com.clinica.backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @GetMapping
    public List<Doctor> listAll() {
        return service.doctorList();
    }

    @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody Doctor doctor){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(doctor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updatePatient(@PathVariable Long id, @RequestBody Doctor doctorNew){
        return service.findById(id)
                 .map(doctor ->  {
                     doctor.setName(doctorNew.getName());
                     doctor.setSpecialty(doctorNew.getSpecialty());
            Doctor updated = service.save(doctor);
            return ResponseEntity.ok(updated);
        })
                 .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        return service.findById(id)
                .map(doctor -> {
                    service.delete(doctor);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
