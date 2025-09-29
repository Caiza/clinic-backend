package com.clinica.backend.service;

import com.clinica.backend.dto.Patient;
import com.clinica.backend.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PatientServiceTest {

    @Mock
    private PatientRepository repository;

    @InjectMocks
    private PatientService service;

    private Patient patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        patient = new Patient();
        patient.setName("Maria Silva");
        patient.setId(10L);
    }

    @Test
    void testSave(){
        when(repository.save(patient)).thenReturn(patient);
        Patient result = service.save(patient);
        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Maria Silva", result.getName());
        verify(repository, times(1)).save(patient);
    }

    @Test
    void testDelete() {
        doNothing().when(repository).delete(patient);
        service.delete(patient);
        verify(repository, times(1)).delete(patient);
    }

    @Test
    void testFindById(){
        when(repository.findById(1L)).thenReturn(Optional.of(patient));
        Optional<Patient> result = service.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Maria Silva", result.get().getName());
        verify(repository, times(1)).findById(1L);
    }

}
