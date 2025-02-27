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

    Optional<Appointment> findAppointmentById(Long id);

    Optional<Appointment> findAppointmentByPatientId(Long patientId);

    Optional<Appointment> findAppointmentByDateAndTime(LocalDate date, LocalDateTime time);

    static Optional<Appointment> findAppointmentByDate(LocalDate date) {
        return null;
    }

}
