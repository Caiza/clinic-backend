package com.clinica.backend.controller;

import com.clinica.backend.dto.Consult;
import com.clinica.backend.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consult")
public class ConsultController {

    @Autowired
    private ConsultService service;

    @GetMapping
    public List<Consult> listAll() {
        return service.consultList();
    }

    @PostMapping
    public ResponseEntity<Consult> create(@RequestBody Consult consult){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(consult));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consult> updatePatient(@PathVariable Long id, @RequestBody Consult consultNew){
        return service.findById(id)
                 .map(consult ->  {
                     consult.setPatient(consultNew.getPatient());
                     consult.setDate(consultNew.getDate());
                     consult.setStatus(consultNew.getStatus());
                     consult.setDoctor(consultNew.getDoctor());
            Consult updated = service.save(consult);
            return ResponseEntity.ok(updated);
        })
                 .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        return service.findById(id)
                .map(consult -> {
                    service.delete(consult);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
