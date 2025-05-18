package com.cg.training.exceptions;

/**
 * This class represents a custom exception for handling invalid appointment-related scenarios.
 * It is used to throw errors when an appointment cannot be booked or completed due to certain conditions.
 * 
 * For example:
 * - No available doctors when booking an appointment.
 * - Trying to complete an already completed appointment.
 * - Providing an invalid appointment index.
 * 
 * @author Sanjona Bhattacharjee
 */
@SuppressWarnings("serial")
public class InvalidAppointmentException extends RuntimeException {

    /**
     * Constructor to create an InvalidAppointmentException with a specific message.
     * 
     * @param message the detail message explaining the exception.
     */
    public InvalidAppointmentException(String message) {
        super(message);
    }
}