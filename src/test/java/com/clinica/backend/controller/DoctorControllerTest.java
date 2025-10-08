package com.clinica.backend.controller;

import com.clinica.backend.dto.Doctor;
import com.clinica.backend.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = DoctorController.class)
@AutoConfigureMockMvc(addFilters = false)
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    // 👇 ignora autenticação (não carrega JwtAuthFilter nem SecurityFilterChain)
    @MockBean
    private com.clinica.backend.auth.JwtAuthFilter jwtAuthFilter;

    @Test
    void testListAllDoctors() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. House");
        doctor.setSpecialty("Diagnóstico");

        when(doctorService.doctorList()).thenReturn(Arrays.asList(doctor));

        mockMvc.perform(get("/api/doctor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dr. House"));
    }

    @Test
    void testCreateDoctor() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. Strange");
        doctor.setSpecialty("Cirurgia");

        when(doctorService.save(doctor)).thenReturn(doctor);

        mockMvc.perform(post("/api/doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Dr. Strange\",\"specialty\":\"Cirurgia\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Dr. Strange"));
    }

    @Test
    void testUpdateDoctor() throws Exception {
        Doctor existingDoctor = new Doctor();
        existingDoctor.setId(1L);
        existingDoctor.setName("Dr. Old");
        existingDoctor.setSpecialty("Clinico");

        Doctor updatedDoctor = new Doctor();
        updatedDoctor.setId(1L);
        updatedDoctor.setName("Dr. New");
        updatedDoctor.setSpecialty("Cardiologia");

        when(doctorService.findById(1L)).thenReturn(Optional.of(existingDoctor));
        when(doctorService.save(existingDoctor)).thenReturn(updatedDoctor);

        mockMvc.perform(put("/api/doctor/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Dr. New\",\"specialty\":\"Cardiologia\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. New"));
    }

    @Test
    void testDeleteDoctor() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. Delete");

        when(doctorService.findById(1L)).thenReturn(Optional.of(doctor));

        mockMvc.perform(delete("/api/doctor/1"))
                .andExpect(status().isNoContent());
    }
}