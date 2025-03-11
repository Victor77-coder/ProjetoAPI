package com.projetoAPI.controller;

import com.projetoAPI.domain.user.Appointment;
import com.projetoAPI.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) throws Exception {
        Appointment createdAppointment = appointmentService.isAppointmentAvailable(appointment);
        URI location = URI.create("/appointments/" + createdAppointment.getId());
        return ResponseEntity.created(location).build(); // Retorna o status 201 Created
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<Appointment>> findAppointmentsByDate(@PathVariable String date) {
        List<Appointment> appointments = appointmentService.findAppointmentsByDate(LocalDate.parse(date));
        return ResponseEntity.ok(appointments); // Retorna o status 200 OK
    }
}
