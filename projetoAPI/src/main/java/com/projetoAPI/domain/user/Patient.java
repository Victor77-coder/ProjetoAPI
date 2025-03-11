package com.projetoAPI.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "patients")
@Table(name = "patients")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id; // ID DO PACIENTE

    private String name; // NOME DO PACIENTE

    @Column(unique = true)
    private String email; // EMAIL DO PACIENTE

    @Column(name = "phone_number", unique = true)
    private String phone; // TELEFONE DO PACIENTE

    @Column(unique = true)
    private String cpf; // CPF DO PACIENTE

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments; // LISTA DE CONSULTAS DO PACIENTE
}

