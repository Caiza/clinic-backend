package com.clinica.backend.exception;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(Long id){
        super("Patient not found: " + id);
    }
}
