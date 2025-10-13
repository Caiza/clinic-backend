package com.clinica.backend.controller;

import com.clinica.backend.dto.Doctor;
import com.clinica.backend.exception.DoctorNotFoundException;
import com.clinica.backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @GetMapping
    public ResponseEntity<Page<Doctor>> listAll(Pageable pageable) {
        Page<Doctor> result = service.doctorList(pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody Doctor doctor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(doctor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorNew) {
        Doctor updated = service.findById(id)
                .map(doctor -> {
                    doctor.setName(doctorNew.getName());
                    doctor.setSpecialty(doctorNew.getSpecialty());
                    return service.save(doctor);

                }).orElseThrow(() -> new DoctorNotFoundException(id));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        service.findById(id)
                .ifPresentOrElse(
                        doctor -> service.delete(doctor),
                        () -> {
                            throw new DoctorNotFoundException(id);
                        }
                );
        return ResponseEntity.noContent().build();
    }


}
