package com.projetoAPI.dtos;

import com.projetoAPI.domain.user.Appointment;
import com.projetoAPI.domain.user.AppointmentStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
public class DtoAppointment {
    public record AppointmentDTO(Long id, Long patientId, LocalDate date, LocalDateTime time, AppointmentStatus status) {} // DTO para retornar os dados da consulta

    public record CreateAppointmentDTO(
            @NotNull Long patientId, // Id do paciente
            @NotNull LocalDate date, // Data da consulta
            @NotNull LocalDateTime time) {} // DTO para criar uma consulta

    public record UpdateAppointmentStatusDTO(Long id, AppointmentStatus status) {} // DTO para atualizar o status da consulta

    public record PatientDTO(Long id, String name, String cpf, String email, String phone) {} // DTO para retornar os dados do paciente
}


