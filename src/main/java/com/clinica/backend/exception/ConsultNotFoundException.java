package com.clinica.backend.exception;

public class ConsultNotFoundException extends RuntimeException{
    public ConsultNotFoundException(Long id){
        super("Consult not found: " + id);
    }
}
