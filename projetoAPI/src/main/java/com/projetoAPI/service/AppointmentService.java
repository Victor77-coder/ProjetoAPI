package com.projetoAPI.service;

import com.projetoAPI.domain.user.Appointment;
import com.projetoAPI.domain.user.AppointmentStatus;
import com.projetoAPI.dtos.DtoAppointment;
import com.projetoAPI.exceptions.AppointmentNotAvailableException;
import com.projetoAPI.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository repository;

    public DtoAppointment.AppointmentDTO isAppointmentAvailable(Appointment appointment) {
        if (repository.findAppointmentByDateAndTime(appointment.getDate(), appointment.getTime()).isPresent()) {
            throw new AppointmentNotAvailableException("Appointment not available");
        } // Caso a consulta já exista, uma exceção é lançada

        appointment.setStatus(AppointmentStatus.AGENDADO);
        Appointment savedAppointment = repository.save(appointment);
        return new DtoAppointment.AppointmentDTO(
                savedAppointment.getId(),
                savedAppointment.getPatient().getId(),
                savedAppointment.getDate(),
                savedAppointment.getTime(),
                savedAppointment.getStatus()
        ); // Retorna o objeto criado
    }

    public DtoAppointment.AppointmentDTO updateAppointmentStatus(Long id, AppointmentStatus status) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new AppointmentNotAvailableException("Appointment not found"));
        appointment.setStatus(status);
        Appointment updatedAppointment = repository.save(appointment);
        return new DtoAppointment.AppointmentDTO(
                updatedAppointment.getId(),
                updatedAppointment.getPatient().getId(),
                updatedAppointment.getDate(),
                updatedAppointment.getTime(),
                updatedAppointment.getStatus()
        ); // Retorna o objeto atualizado
    }

    public List<DtoAppointment.AppointmentDTO> findAppointmentsByDate(LocalDate date) {
        return repository.findAppointmentByDate(date).stream()
                .map(appointment -> new DtoAppointment.AppointmentDTO(
                        appointment.getId(),
                        appointment.getPatient().getId(),
                        appointment.getDate(),
                        appointment.getTime(),
                        appointment.getStatus()
                ))
                .collect(Collectors.toList());
    } // Retorna a lista de consultas marcadas para a data informada
}