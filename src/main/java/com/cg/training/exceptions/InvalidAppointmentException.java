package com.cg.training.exceptions;

@SuppressWarnings("serial")
public class InvalidAppointmentException extends RuntimeException {
    public InvalidAppointmentException(String message) {
        super(message);
    }
    
}