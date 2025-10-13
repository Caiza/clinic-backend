package com.clinica.backend.controller;

import com.clinica.backend.dto.Consult;
import com.clinica.backend.service.ConsultService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(ConsultController.class)
@AutoConfigureMockMvc(addFilters = false)
class ConsultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsultService service;

    @MockBean
    private com.clinica.backend.auth.JwtAuthFilter jwtAuthFilter;

    @MockBean
    private com.clinica.backend.util.JwtUtil jwtUtil;

    @Test
    void testListAll() throws Exception {
        Consult consult = new Consult();
        consult.setId(1L);
        consult.setStatus("Agendada");

        Page<Consult> page = new PageImpl<>(Collections.singletonList(consult), PageRequest.of(0, 10), 1);
        when(service.consultList(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/consult"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].status").value("Agendada"));
    }

    @Test
    void testCreate() throws Exception {
        Consult consult = new Consult();
        consult.setId(1L);
        consult.setStatus("Criada");

        when(service.save(any(Consult.class))).thenReturn(consult);

        mockMvc.perform(post("/api/consult")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\": \"Criada\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("Criada"));
    }

    @Test
    void testUpdatePatientFound() throws Exception {
        Consult existing = new Consult();
        existing.setId(1L);
        existing.setStatus("Agendada");

        Consult updated = new Consult();
        updated.setId(1L);
        updated.setStatus("Atualizada");

        when(service.findById(1L)).thenReturn(Optional.of(existing));
        when(service.save(any(Consult.class))).thenReturn(updated);

        mockMvc.perform(put("/api/consult/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\": \"Atualizada\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Atualizada"));
    }

    @Test
    void testUpdatePatientNotFound() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/consult/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\": \"Atualizada\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeletePatientFound() throws Exception {
        Consult consult = new Consult();
        consult.setId(1L);

        when(service.findById(1L)).thenReturn(Optional.of(consult));
        doNothing().when(service).delete(consult);

        mockMvc.perform(delete("/api/consult/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).delete(consult);
    }

    @Test
    void testDeletePatientNotFound() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/consult/1"))
                .andExpect(status().isNotFound());
    }
}