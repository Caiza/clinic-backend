package com.clinica.backend.service;

import com.clinica.backend.dto.Consult;
import com.clinica.backend.dto.Doctor;
import com.clinica.backend.dto.Patient;
import com.clinica.backend.repository.ConsultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsultServiceTest {

    @Mock
    private ConsultRepository repository;

    @InjectMocks
    private ConsultService service;

    private Consult consult;
    private Patient patient;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        patient = new Patient();
        patient.setName("Maria Silva");
        patient.setId(10L);

        doctor = new Doctor();
        doctor.setId(20L);
        doctor.setName("Dr. Joao");

        consult = new Consult();
        consult.setId(1L);
        consult.setDate("2025-09-23");
        consult.setStatus("Agendada");
        consult.setDoctor(doctor);
        consult.setPatient(patient);

    }

    @Test
    void testSave(){
        when(repository.save(consult)).thenReturn(consult);
        Consult result = service.save(consult);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Agendada", result.getStatus());
        verify(repository, times(1)).save(consult);

    }

    @Test
    void testDelete() {
        doNothing().when(repository).delete(consult);
        service.delete(consult);
        verify(repository, times(1)).delete(consult);
    }

    @Test
    void testFindById(){
        when(repository.findById(1L)).thenReturn(Optional.of(consult));
        Optional<Consult> result = service.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Agendada", result.get().getStatus());
        assertEquals("Maria Silva", result.get().getPatient().getName());
        verify(repository, times(1)).findById(1L);
    }

}
