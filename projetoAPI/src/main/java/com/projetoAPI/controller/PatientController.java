package com.projetoAPI.controller;

import com.projetoAPI.domain.user.Patient;
import com.projetoAPI.dtos.DtoPatient;
import com.projetoAPI.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("api/v1/patients")
@RestController
public class PatientController {
    @Autowired
    private PatientRepository repository;

    @PostMapping("/create") // Cria um paciente
    public ResponseEntity<DtoPatient.PatientDTO> createPatient(@Validated @RequestBody DtoPatient.CreatePatientDTO createPatientDTO) {
        Patient patient = new Patient(
                null,// Id é gerado automaticamente
                createPatientDTO.name(),
                createPatientDTO.cpf(),
                createPatientDTO.email(),
                createPatientDTO.phone(),
                null // Lista de consultas é inicializada como nula
        );

        Patient savedPatient = repository.save(patient); // Salva o paciente no banco de dados
        URI location = URI.create("/api/v1/patients/" + savedPatient.getId()); // Cria a URI para o novo paciente

        return ResponseEntity.created(location).body(new DtoPatient.PatientDTO(
                savedPatient.getId(),
                savedPatient.getName(),
                savedPatient.getCpf(),
                savedPatient.getEmail(),
                savedPatient.getPhone()
        ));
    }

    @GetMapping("/{id}") // Pega o paciente pelo ID
    public Optional<ResponseEntity<DtoPatient.PatientDTO>> getPatientById(@PathVariable Long id) {
        return repository.findById(id)
                .map(patient -> ResponseEntity.ok(new DtoPatient.PatientDTO( // Mapeia o paciente para um DTO
                        patient.getId(),
                        patient.getName(),
                        patient.getCpf(),
                        patient.getEmail(),
                        patient.getPhone()
                ))); // Retorna o paciente
    }

    @GetMapping("/all") // Pega todos os pacientes
    public ResponseEntity<List<DtoPatient.PatientDTO>> findAllPatients() {
        List<DtoPatient.PatientDTO> patients = repository.findAll().stream()
                .map(patient -> new DtoPatient.PatientDTO( // Mapeia os pacientes para DTOs
                        patient.getId(),
                        patient.getName(),
                        patient.getCpf(),
                        patient.getEmail(),
                        patient.getPhone()
                ))
                .collect(Collectors.toList()); // Converte a lista de pacientes para uma lista de DTOs

        return ResponseEntity.ok(patients);
    }

}
