package com.projetoAPI.repositories;

import com.projetoAPI.domain.user.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findAppointmentByPatientId(Long patientId); // Retorna uma consulta de um determinado paciente
    Optional<Appointment> findAppointmentByDateAndTime(LocalDate date, LocalDateTime time); // Retorna uma consulta de uma determinada data e horário
    List<Appointment> findAppointmentByDate(LocalDate date); // Retorna todas as consultas de uma determinada data
}