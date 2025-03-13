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

@RestController // Indica que a classe é um controller
@RequestMapping("api/v1/appointments") // Caminho para acessar os endpoints
@Validated // Validação dos campos
public class AppointmentController {

    @Autowired // Injeção de dependência
    private AppointmentService appointmentService;

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/create") // Endpoint para criar uma consulta
    public ResponseEntity<AppointmentDTO> createAppointment(@Validated @RequestBody CreateAppointmentDTO createAppointmentDTO) {
        Patient patient = patientRepository.findById(createAppointmentDTO.patientId())
                .orElseThrow(() -> new PatientNotFoundException("Patient not found")); // Caso o paciente não seja encontrado, uma exceção é lançada

        Appointment appointment = new Appointment(
                null, // Id é gerado automaticamente
                patient,
                createAppointmentDTO.date(),
                createAppointmentDTO.time(),
                AppointmentStatus.AGENDADO // Status padrão
        );

        AppointmentDTO createdAppointment = appointmentService.isAppointmentAvailable(appointment);
        URI location = URI.create("/api/v1/appointments/" + createdAppointment.id());
        return ResponseEntity.created(location).body(createdAppointment); // Retorna o objeto criado
    }

    @GetMapping("/{date}") // Endpoint para buscar consultas por data
    public ResponseEntity<List<AppointmentDTO>> findAppointmentsByDate(@PathVariable String date) {
        List<AppointmentDTO> appointments = appointmentService.findAppointmentsByDate(LocalDate.parse(date));
        return ResponseEntity.ok(appointments);
    } // Retorna a lista de consultas marcadas para a data informada
}