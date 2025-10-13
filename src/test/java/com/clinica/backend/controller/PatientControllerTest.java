package com.clinica.backend.controller;

import com.clinica.backend.dto.Patient;
import com.clinica.backend.service.PatientService;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private com.clinica.backend.auth.JwtAuthFilter jwtAuthFilter;

    @Test
    void testListAllDoctors() throws Exception {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Joao Roberto");
        patient.setEmail("sdcsjkifj@gmail.com");
        patient.setObservations("sdfsddsgvdfgbf");
        patient.setPhone("43534546");
        patient.setDateBirth(LocalDate.of(1999, 5, 5));

        Page<Patient> page = new PageImpl<>(Collections.singletonList(patient), PageRequest.of(0, 10), 1);
        when(patientService.patientList(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/patient"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Joao Roberto"));
    }

    @Test
    void testCreateDoctor() throws Exception {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Joao Roberto");
        patient.setEmail("sdcsjkifj@gmail.com");
        patient.setObservations("sdfsddsgvdfgbf");
        patient.setPhone("43534546");
        patient.setDateBirth(LocalDate.of(1999, 5, 5));

        when(patientService.save(any(Patient.class))).thenReturn(patient);

        mockMvc.perform(post("/api/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Joao Roberto\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Joao Roberto"));
    }

    @Test
    void testUpdateDoctor() throws Exception {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Joao Roberto");
        patient.setEmail("sdcsjkifj@gmail.com");
        patient.setObservations("sdfsddsgvdfgbf");
        patient.setPhone("43534546");
        patient.setDateBirth(LocalDate.of(1999, 5, 5));

        Patient updatedPatient = new Patient();
        updatedPatient.setId(1L);
        updatedPatient.setName("Carlos Robertos");

        when(patientService.findById(1L)).thenReturn(Optional.of(updatedPatient));
        when(patientService.save(updatedPatient)).thenReturn(updatedPatient);

        mockMvc.perform(put("/api/patient/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Carlos Robertos\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Carlos Robertos"));
    }

    @Test
    void testDeleteDoctor() throws Exception {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Joao Roberto");
        patient.setEmail("sdcsjkifj@gmail.com");
        patient.setObservations("sdfsddsgvdfgbf");
        patient.setPhone("43534546");
        patient.setDateBirth(LocalDate.of(1999, 5, 5));

        when(patientService.findById(1L)).thenReturn(Optional.of(patient));

        mockMvc.perform(delete("/api/patient/1"))
                .andExpect(status().isNoContent());
    }
}