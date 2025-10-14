package com.clinica.backend.integration;

import com.clinica.backend.dto.Exam;
import com.clinica.backend.repository.ExamRepository;
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
public class ExamIntegrationTest {

    @Autowired
    private ExamRepository examRepository;

    @Test
    void shouldCreateAndFindConsult() {
        // Arrange
        examRepository.deleteAll();

        Exam exam = new Exam();
        exam.setName("Raio-X de Tórax");

        // Act
        examRepository.save(exam);
        List<Exam> result = examRepository.findAll();

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Raio-X de Tórax");
    }
}
