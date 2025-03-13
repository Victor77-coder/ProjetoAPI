package com.projetoAPI.exceptions;

public class AppointmentNotAvailableException extends RuntimeException { // Exceção para consultas não disponíveis
    public AppointmentNotAvailableException(String message) {
        super(message); // Mensagem de erro
    }
}
