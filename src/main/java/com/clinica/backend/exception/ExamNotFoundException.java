package com.clinica.backend.exception;

public class ExamNotFoundException extends RuntimeException{
    public ExamNotFoundException(Long id){
        super("Exam not found: " + id);
    }
}
