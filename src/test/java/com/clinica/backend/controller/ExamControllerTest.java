package com.clinica.backend.controller;

import com.clinica.backend.dto.Exam;
import com.clinica.backend.service.ExamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = ExamController.class)
@AutoConfigureMockMvc(addFilters = false)
class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService examService;

    @MockBean
    private com.clinica.backend.auth.JwtAuthFilter jwtAuthFilter;

    @Test
    void testListAllDoctors() throws Exception {
        Exam exam = new Exam();
        exam.setId(1L);
        exam.setName("Complete Blood Count (CBC)");

        Page<Exam> page = new PageImpl<>(Collections.singletonList(exam), PageRequest.of(0, 10), 1);
        when(examService.examList(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/exam"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Complete Blood Count (CBC)"));
    }

    @Test
    void testCreateDoctor() throws Exception {
        Exam exam = new Exam();
        exam.setId(1L);
        exam.setName("Blood Glucose Test");

        when(examService.save(any(Exam.class))).thenReturn(exam);

        mockMvc.perform(post("/api/exam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Blood Glucose Test\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Blood Glucose Test"));
    }

    @Test
    void testUpdateDoctor() throws Exception {
        Exam existingExam = new Exam();
        existingExam.setId(1L);
        existingExam.setName("Total Cholesterol Test");

        Exam updatedExam = new Exam();
        updatedExam.setId(1L);
        updatedExam.setName("Total Cholesterol Test");

        when(examService.findById(1L)).thenReturn(Optional.of(existingExam));
        when(examService.save(existingExam)).thenReturn(existingExam);

        mockMvc.perform(put("/api/exam/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Total Cholesterol Test\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Total Cholesterol Test"));
    }

    @Test
    void testDeleteDoctor() throws Exception {
        Exam exam = new Exam();
        exam.setId(1L);
        exam.setName("Vitamin D Test");

        when(examService.findById(1L)).thenReturn(Optional.of(exam));

        mockMvc.perform(delete("/api/exam/1"))
                .andExpect(status().isNoContent());
    }
}