package com.projetoAPI.repositories;

import com.projetoAPI.domain.user.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findPatientByCpf(String cpf);
    Optional<Patient> findPatientById(Long id);
}
