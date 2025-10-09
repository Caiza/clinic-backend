package com.clinica.backend.exception;

public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException(Long id){
        super("Doctor not found: " + id);
    }
}
