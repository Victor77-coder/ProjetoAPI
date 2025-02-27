package com.projetoAPI.controller;

import com.projetoAPI.domain.user.Appointment;
import com.projetoAPI.repositories.AppointmentRepository;
import com.projetoAPI.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

   public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) throws Exception {
       Appointment createdAppointment = appointmentService.isAppointmentAvailable(appointment);
        URI location = URI.create("/appointments/" + createdAppointment.getId());
        return ResponseEntity.created(location).build();
   }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Appointment>> findAppointmentsByDate(@RequestParam String date) {
        Optional<Appointment> appointments = AppointmentRepository.findAppointmentByDate(LocalDate.parse(date));
        return ResponseEntity.ok(appointments);
    }
}
