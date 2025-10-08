package com.clinica.backend.service;

import com.clinica.backend.dto.Consult;
import com.clinica.backend.dto.Doctor;
import com.clinica.backend.dto.Patient;
import com.clinica.backend.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ActiveProfiles("test")
public class DoctorServiceTest {

    @Mock
    private DoctorRepository repository;

    @InjectMocks
    private DoctorService service;

    private Consult consult;
    private Patient patient;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        doctor = new Doctor();
        doctor.setId(20L);
        doctor.setName("Dr. Joao");
    }

    @Test
    void testSave(){
        when(repository.save(doctor)).thenReturn(doctor);
        Doctor result = service.save(doctor);
        assertNotNull(result);
        assertEquals(20L, result.getId());
        assertEquals("Dr. Joao", result.getName());
        verify(repository, times(1)).save(doctor);

    }

    @Test
    void testDelete() {
        doNothing().when(repository).delete(doctor);
        service.delete(doctor);
        verify(repository, times(1)).delete(doctor);
    }

    @Test
    void testFindById(){
        when(repository.findById(10L)).thenReturn(Optional.of(doctor));
        Optional<Doctor> result = service.findById(10L);
        assertTrue(result.isPresent());
        assertEquals("Dr. Joao", result.get().getName());
        verify(repository, times(1)).findById(10L);
    }

}
