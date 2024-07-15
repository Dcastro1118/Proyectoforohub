package com.proyecto.forohub.infra.excepciones;

public class IntegrityValidation extends RuntimeException{
    public IntegrityValidation(String message) {
        super(message);
    }
}
