package com.projetoAPI.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate date;

    @Column(name = "appointment_time", nullable = false)
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_status", nullable = false)
    private AppointmentStatus status = AppointmentStatus.AGENDADO;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Appointment(Long id, Patient patient, LocalDate date, LocalDateTime time, AppointmentStatus status) {
        this.patient = patient;
        this.date = date;
        this.time = time;
        this.status = status != null ? status : AppointmentStatus.AGENDADO;
        this.createdAt = LocalDateTime.now();
    } // Construtor para criação de consultas

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}