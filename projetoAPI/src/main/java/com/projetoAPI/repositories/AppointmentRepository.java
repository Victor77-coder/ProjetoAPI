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

    Optional<Appointment> findById(Long id); // Retorna uma consulta por ID

    Optional<Appointment> findAppointmentByPatientId(Long patientId); // Retorna uma consulta por ID do paciente

    Optional<Appointment> findAppointmentByDateAndTime(LocalDate date, LocalDateTime time); // Retorna uma consulta por data e horário

    List<Appointment> findAppointmentByDate(LocalDate date);  // Retorna uma lista de consultas por data
}
