package com.projetoAPI.dtos;

import com.projetoAPI.domain.user.Appointment;
import com.projetoAPI.domain.user.AppointmentStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
public class DtoAppointment {
    public record AppointmentDTO(Long id, Long patientId, LocalDate date, LocalDateTime time, AppointmentStatus status) {}

    public record CreateAppointmentDTO(
            @NotNull Long patientId,
            @NotNull LocalDate date,
            @NotNull LocalDateTime time) {}

    public record UpdateAppointmentStatusDTO(Long id, AppointmentStatus status) {}

    public record PatientDTO(Long id, String name, String cpf, String email, String phone) {}
}


