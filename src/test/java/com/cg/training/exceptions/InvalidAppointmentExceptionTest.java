package com.cg.training.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvalidAppointmentExceptionTest {

    @Test(expected = InvalidAppointmentException.class)
    public void testInvalidAppointmentExceptionIsThrown() {
        // Simulate a condition that would throw the exception
        throw new InvalidAppointmentException("Invalid appointment details");
    }

    @Test
    public void testInvalidAppointmentExceptionMessage() {
        String expectedMessage = "Invalid appointment details";
        InvalidAppointmentException exception = new InvalidAppointmentException(expectedMessage);
        
        // Assert that the exception message is as expected
        assertEquals(expectedMessage, exception.getMessage());
    }
}
