package com.projetoAPI.exceptions;

public class PatientNotFoundException extends RuntimeException { // Exceção para paciente não encontrado
    public PatientNotFoundException(String message) {
        super(message);
    } // Mensagem de erro
}
