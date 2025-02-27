package com.projetoAPI.service;

import com.projetoAPI.domain.user.Appointment;
import com.projetoAPI.domain.user.AppointmentStatus;
import com.projetoAPI.repositories.AppointmentRepository;
import com.projetoAPI.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository repository;
    @Autowired
    private PatientRepository patientRepository;
    public Appointment isAppointmentAvailable(Appointment appointment) throws Exception {
        Optional<Appointment> existingAppointments = repository.findAppointmentByDateAndTime(appointment.getDate(), appointment.getTime());

        if (existingAppointments.isEmpty()) {
            appointment.setStatus(AppointmentStatus.AGENDADO);
            return repository.save(appointment);
        } else {
            throw new Exception("Appointment not available");
        }
    }

    public Appointment updateAppointmentStatus(Long id, AppointmentStatus status) throws Exception {
        Appointment appointment = repository.findAppointmentById(id).orElseThrow(() -> new Exception("Appointment not found"));
        appointment.setStatus(status);
        return repository.save(appointment);
    }
}
