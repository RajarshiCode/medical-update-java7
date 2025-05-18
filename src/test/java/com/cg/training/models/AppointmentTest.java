package com.cg.training.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cg.training.models.Appointment;
import com.cg.training.models.Doctor;
import com.cg.training.models.Patient;
import com.cg.training.service.AppointmentSystem;
import com.cg.training.exceptions.InvalidAppointmentException;

/**
 * This class contains unit tests for the Appointment, Doctor, and Patient classes
 * using the AppointmentSystem. It checks for correct appointment creation,
 * completion, validation, and error handling.
 * <p>
 * Written in a simple format for Java beginners to understand.
 * </p>
 * 
 * @author Sreejit Sarkar
 */
public class AppointmentTest {

    private Doctor doctor;
    private Patient patient;
    private Appointment appointment;
    private AppointmentSystem appointmentSystem;

    /**
     * This method runs before each test.
     * It sets up an AppointmentSystem and creates a doctor, a patient,
     * and books one appointment.
     */
    @Before
    public void setUp() {
        appointmentSystem = new AppointmentSystem();

        appointmentSystem.registerDoctor("DrSushir");
        appointmentSystem.registerPatient("Ram");

        doctor = appointmentSystem.doctors.get(0);
        patient = appointmentSystem.patients.get(0);

        appointment = appointmentSystem.bookAppointment(patient);
    }

    /**
     * Tests whether an appointment is correctly created
     * with valid doctor, patient, and default status.
     */
    @Test
    public void testAppointmentCreation() {
        assertNotNull("Appointment should not be null", appointment);
        assertEquals("Doctor name should match", "DrSushir", appointment.doctor.getName());
        assertEquals("Patient name should match", "Ram", appointment.patient.getName());
        assertEquals("Status should be Scheduled", "Scheduled", appointment.getStatus());
        assertFalse("Doctor should be unavailable", doctor.isAvailable());
    }

    /**
     * Tests whether the appointment can be completed successfully.
     * After completion, the status should change and the doctor should be available.
     */
    @Test
    public void testCompleteAppointment() {
        appointment.completeAppointment();

        assertEquals("Status should be Completed", "Completed", appointment.getStatus());
        assertTrue("Doctor should be available after appointment completion", doctor.isAvailable());
    }

    /**
     * Tests the getStatus method before and after completing an appointment.
     */
    @Test
    public void testGetStatus() {
        assertEquals("Status should be Scheduled initially", "Scheduled", appointment.getStatus());

        appointment.completeAppointment();
        assertEquals("Status should be Completed after completion", "Completed", appointment.getStatus());
    }

    /**
     * Tests whether appointment details are displayed correctly before
     * and after completion.
     */
    @Test
    public void testAppointmentDetails() {
        String expectedDetails = "Appointment: Patient[Ram] - Doctor[DrSushir] - Status: Scheduled";
        assertEquals("Appointment details should match", expectedDetails, appointment.appointmentDetails());

        appointment.completeAppointment();
        expectedDetails = "Appointment: Patient[Ram] - Doctor[DrSushir] - Status: Completed";
        assertEquals("Appointment details should match after completion", expectedDetails, appointment.appointmentDetails());
    }

    /**
     * Tests that creating a doctor with an invalid name throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDoctorName() {
        new Doctor("D1002", "Dr123");
    }

    /**
     * Tests that creating a patient with an invalid name throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPatientName() {
        new Patient("P1002", "123Ram");
    }

    /**
     * Tests that booking an appointment when no doctors are available throws an exception.
     */
    @Test(expected = InvalidAppointmentException.class)
    public void testNoAvailableDoctorForAppointment() {
        doctor.setAvailable(false);
        appointmentSystem.bookAppointment(patient);
    }

    /**
     * Tests that trying to complete an already completed appointment throws an exception.
     */
    @Test(expected = InvalidAppointmentException.class)
    public void testCompleteAlreadyCompletedAppointment() {
        appointment.completeAppointment();
        appointmentSystem.completeAppointment(0); // Trying to complete again
    }
}