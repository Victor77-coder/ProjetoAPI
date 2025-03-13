package com.projetoAPI.dtos;

public class DtoPatient {

    public record PatientDTO(Long id, String name, String cpf, String email, String phone) {} // DTO para retornar os dados do paciente

    public record CreatePatientDTO( // DTO para criar um paciente
            String name, // Nome do paciente
            String cpf, // CPF do paciente
            String email, // Email do paciente
            String phone) {} // Telefone do paciente
}
