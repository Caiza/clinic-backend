package com.clinica.backend.integration;

import com.clinica.backend.dto.Doctor;
import com.clinica.backend.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class DoctorIntegrationTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    void shouldCreateAndFindConsult() {
        // Arrange
        doctorRepository.deleteAll();

        // Criar médico
        Doctor doctor = new Doctor();
        doctor.setName("Dr. João Santos");
        doctor.setSpecialty("Cardiologia");
        doctorRepository.save(doctor);

        // Act
        doctorRepository.save(doctor);
        List<Doctor> result = doctorRepository.findAll();

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Dr. João Santos");
    }
}
