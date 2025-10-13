package com.clinica.backend.repository;

import com.clinica.backend.dto.Consult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {
    Page<Consult> findyPatientNameContaingIgnoreCase(String patientName, Pageable pageable);
}
