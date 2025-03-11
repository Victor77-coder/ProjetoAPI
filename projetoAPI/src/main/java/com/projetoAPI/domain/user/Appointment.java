package com.projetoAPI.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "appointments")
@Table(name = "appointments")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // ID DA CONSULTA

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient; // PACIENTE DA CONSULTA

    @Column(name = "appointment_date", nullable = false)
    private LocalDate date; // DATA DA CONSULTA

    @Column(name = "appointment_time", nullable = false)
    private LocalDateTime time; // HORÁRIO DA CONSULTA

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_status", nullable = false)
    private AppointmentStatus status = AppointmentStatus.AGENDADO; // STATUS DA CONSULTA

    @CreatedDate
    private LocalDateTime createdAt; // DATA DE CRIAÇÃO DA CONSULTA

    @LastModifiedDate
    private LocalDateTime updatedAt; // DATA DE ATUALIZAÇÃO DA CONSULTA
}
