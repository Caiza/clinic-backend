package com.clinica.backend.controller;

import com.clinica.backend.dto.Consult;
import com.clinica.backend.exception.ConsultNotFoundException;
import com.clinica.backend.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consult")
public class ConsultController {

    @Autowired
    private ConsultService service;

    @GetMapping
    public ResponseEntity<Page<Consult>> listAll(Pageable pageable) {
        Page<Consult> result = service.consultList(pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Consult> create(@RequestBody Consult consult) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(consult));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consult> updateConsult(@PathVariable Long id, @RequestBody Consult consultNew) {
        Consult updated = service.findById(id)
                .map(consult -> {
                    consult.setPatient(consultNew.getPatient());
                    consult.setDate(consultNew.getDate());
                    consult.setStatus(consultNew.getStatus());
                    consult.setDoctor(consultNew.getDoctor());
                    return service.save(consult);

                }).orElseThrow(() -> new ConsultNotFoundException(id));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsult(@PathVariable Long id) {
        service.findById(id).ifPresentOrElse(
                consult -> service.delete(consult),
                () -> {
                    throw new ConsultNotFoundException(id);
                }
        );
        return ResponseEntity.noContent().build();
    }


}
