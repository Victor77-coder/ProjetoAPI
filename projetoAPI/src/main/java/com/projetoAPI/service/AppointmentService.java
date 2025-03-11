package com.projetoAPI.service;

import com.projetoAPI.domain.user.Appointment;
import com.projetoAPI.domain.user.AppointmentStatus;
import com.projetoAPI.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository repository;

    public Appointment isAppointmentAvailable(Appointment appointment) throws Exception {
        if (repository.findAppointmentByDateAndTime(appointment.getDate(), appointment.getTime()).isPresent()) {
            throw new Exception("Appointment not available");
        }

        appointment.setStatus(AppointmentStatus.AGENDADO);
        return repository.save(appointment);
    }

    public Appointment updateAppointmentStatus(Long id, AppointmentStatus status) throws Exception {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new Exception("Appointment not found"));
        appointment.setStatus(status);
        return repository.save(appointment);
    }

    public List<Appointment> findAppointmentsByDate(LocalDate date) {
        return repository.findAppointmentByDate(date);
    }
}
