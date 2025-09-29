package com.clinica.backend.service;

import com.clinica.backend.dto.Exam;
import com.clinica.backend.repository.ExamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExamServiceTest {

    @Mock
    private ExamRepository repository;

    @InjectMocks
    private ExamService service;

    private Exam exam;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        exam = new Exam();
        exam.setName("Ultrasom");
        exam.setId(10L);
    }

    @Test
    void testSave(){
        when(repository.save(exam)).thenReturn(exam);
        Exam result = service.save(exam);
        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Ultrasom", result.getName());
        verify(repository, times(1)).save(exam);
    }

    @Test
    void testDelete() {
        doNothing().when(repository).delete(exam);
        service.delete(exam);
        verify(repository, times(1)).delete(exam);
    }

    @Test
    void testFindById(){
        when(repository.findById(1L)).thenReturn(Optional.of(exam));
        Optional<Exam> result = service.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Ultrasom", result.get().getName());
        verify(repository, times(1)).findById(1L);
    }

}
