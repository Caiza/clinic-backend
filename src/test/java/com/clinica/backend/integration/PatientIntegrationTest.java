package com.clinica.backend.integration;

import com.clinica.backend.dto.Patient;
import com.clinica.backend.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class PatientIntegrationTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void shouldCreateAndFindPatient() {
        // Arrange
        patientRepository.deleteAll();
        Patient patient = new Patient();
        patient.setName("Maria Silva");
        patient.setEmail("maria@example.com");
        patient.setPhone("11987654321");
        patient.setObservations("test");
        patient.setDateBirth(LocalDate.of(1999, 5, 5));

        // Act
        patientRepository.save(patient);
        List<Patient> result = patientRepository.findAll();

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Maria Silva");
    }
}
