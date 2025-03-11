package com.projetoAPI.controller;

import com.projetoAPI.domain.user.Appointment;
import com.projetoAPI.domain.user.AppointmentStatus;
import com.projetoAPI.domain.user.Patient;
import com.projetoAPI.dtos.DtoAppointment.AppointmentDTO;
import com.projetoAPI.dtos.DtoAppointment.CreateAppointmentDTO;
import com.projetoAPI.exceptions.PatientNotFoundException;
import com.projetoAPI.service.AppointmentService;
import com.projetoAPI.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/appointments")
@Validated
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/create")
    public ResponseEntity<AppointmentDTO> createAppointment(@Validated @RequestBody CreateAppointmentDTO createAppointmentDTO) {
        Patient patient = patientRepository.findById(createAppointmentDTO.patientId())
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));

        Appointment appointment = new Appointment(
                null, // ID gerado automaticamente
                patient,
                createAppointmentDTO.date(),
                createAppointmentDTO.time(),
                AppointmentStatus.AGENDADO // Definindo o status inicial
        );

        AppointmentDTO createdAppointment = appointmentService.isAppointmentAvailable(appointment);
        URI location = URI.create("/api/v1/appointments/" + createdAppointment.id());
        return ResponseEntity.created(location).body(createdAppointment);
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<AppointmentDTO>> findAppointmentsByDate(@PathVariable String date) {
        List<AppointmentDTO> appointments = appointmentService.findAppointmentsByDate(LocalDate.parse(date));
        return ResponseEntity.ok(appointments);
    }
}