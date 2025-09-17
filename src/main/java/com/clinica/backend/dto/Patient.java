package com.clinica.backend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;



@Entity
@Table(name = "patient")
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required")
    @Size(min = 3, max = 100, message = "name must be 3 to 100 character")
    private String name;

    @Email(message = "Email is invalid")
    @NotBlank(message = "e-mail is required")
    private String email;
    
    private String phone;

    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @Column(columnDefinition = "TEXT")
    private String observations;


}
