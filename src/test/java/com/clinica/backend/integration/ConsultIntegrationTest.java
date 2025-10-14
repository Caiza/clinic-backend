package com.clinica.backend.integration;

import com.clinica.backend.dto.Consult;
import com.clinica.backend.dto.Doctor;
import com.clinica.backend.dto.Exam;
import com.clinica.backend.dto.Patient;
import com.clinica.backend.repository.ConsultRepository;
import com.clinica.backend.repository.DoctorRepository;
import com.clinica.backend.repository.ExamRepository;
import com.clinica.backend.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ConsultIntegrationTest {

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ExamRepository examRepository;

    @Test
    void shouldCreateAndFindConsult() {
        // Arrange
        consultRepository.deleteAll();

        // Criar paciente
        Patient patient = new Patient();
        patient.setName("Maria Silva");
        patient.setEmail("maria@example.com");
        patient.setPhone("11999999999");
        patient.setDateBirth(LocalDate.of(1990, 5, 14));
        patientRepository.save(patient);

        // Criar médico
        Doctor doctor = new Doctor();
        doctor.setName("Dr. João Santos");
        doctor.setSpecialty("Cardiologia");
        doctorRepository.save(doctor);

        Exam exam = new Exam();
        exam.setName("Raio-X de Tórax");
        examRepository.save(exam);

        Consult consult = new Consult();
        consult.setPatient(patient);
        consult.setDoctor(doctor);
        consult.setExam(exam);
        consult.setDate(new Date());

        // Act
        consultRepository.save(consult);
        List<Consult> result = consultRepository.findAll();

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getPatient().getName()).isEqualTo("Maria Silva");
    }
}
