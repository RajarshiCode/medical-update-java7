package com.cg.training.service;

import com.cg.training.exceptions.InvalidAppointmentException;
import com.cg.training.models.Appointment;
import com.cg.training.models.Doctor;
import com.cg.training.models.Patient;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

/**
 * This class contains unit tests for the AppointmentSystem class.
 * It tests functionalities like registering doctors and patients,
 * booking and completing appointments, and handling exceptions.
 * <p>
 * This is written in simple Java for beginners to understand.
 * </p>
 * 
 * @author Rittika Dutta
 */
public class AppointmentSystemTest {

    private AppointmentSystem system;

    /**
     * This method is called before each test. It initializes a new AppointmentSystem.
     */
    @Before
    public void setUp() {
        system = new AppointmentSystem();
    }

    /**
     * Tests if a patient is correctly registered and can be found by ID.
     */
    @Test
    public void testRegisterPatient() {
        system.registerPatient("Ram");
        Patient patient = system.findPatientById("P1000");
        assertNotNull(patient);
        assertEquals("P1000", patient.getId());
        assertEquals("Ram", patient.getName());
    }

    /**
     * Tests that registering a patient with an invalid name throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterInvalidPatient() {
        system.registerPatient("123Ram");
    }

    /**
     * Tests if a doctor is correctly registered and added to the list.
     */
    @Test
    public void testRegisterDoctor() {
        system.registerDoctor("DrSushir");
        List<Doctor> doctors = system.doctors;
        assertEquals(1, doctors.size());
        assertEquals("D1000", doctors.get(0).getId());
    }

    /**
     * Tests that registering a doctor with an invalid name throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterInvalidDoctor() {
        system.registerDoctor("Dr123");
    }

    /**
     * Tests that an appointment is successfully booked with an available doctor.
     */
    @Test
    public void testBookAppointment() {
        system.registerDoctor("DrSushir");
        Doctor doctor = system.doctors.get(0);
        doctor.setAvailable(true);

        Patient patient = new Patient("P2000", "Ram");
        Appointment appointment = system.bookAppointment(patient);

        assertNotNull(appointment);
        assertEquals("P2000", appointment.patient.getId());
        assertEquals("DrSushir", appointment.doctor.getName());
    }

    /**
     * Tests that booking an appointment when no doctor is available throws an exception.
     */
    @Test(expected = InvalidAppointmentException.class)
    public void testBookAppointmentwithnoDoctor() {
        system.registerDoctor("DrSushir");
        Doctor doctor = system.doctors.get(0);
        doctor.setAvailable(false);

        Patient patient = new Patient("P2001", "Sham");
        system.bookAppointment(patient);
    }

    /**
     * Tests that a booked appointment can be completed successfully.
     */
    @Test
    public void testCompleteAppointment() {
        system.registerDoctor("DrSushir");
        Doctor doctor = system.doctors.get(0);
        doctor.setAvailable(true);

        Patient patient = new Patient("P3000", "Laxman");
        Appointment a = system.bookAppointment(patient);

        try {
            system.completeAppointment(0);
            assertEquals("Completed", a.getStatus());
        } catch (InvalidAppointmentException e) {
            fail("Exception should not be thrown");
        }
    }

    /**
     * Tests that completing an appointment with an invalid index throws an exception.
     */
    @Test(expected = InvalidAppointmentException.class)
    public void testCompleteAppointmentinvalidIndex() throws InvalidAppointmentException {
        system.completeAppointment(5);
    }

    /**
     * Tests that completing an already completed appointment throws an exception.
     */
    @Test(expected = InvalidAppointmentException.class)
    public void testCompletedAppointment() throws InvalidAppointmentException {
        system.registerDoctor("DrSushir");
        Doctor doctor = system.doctors.get(0);
        doctor.setAvailable(true);

        Patient patient = new Patient("P4000", "Bharat");
        Appointment a = system.bookAppointment(patient);
        system.completeAppointment(0);
        system.completeAppointment(0); // This should throw exception
    }

    /**
     * Tests that booking fails when all doctors are unavailable.
     */
    @Test(expected = InvalidAppointmentException.class)
    public void testBookAppointmentWithNoAvailableDoctor() {
        system.registerDoctor("DrSushir");
        Doctor doctor = system.doctors.get(0);
        doctor.setAvailable(false); // Doctor unavailable

        Patient patient = new Patient("P2002", "Jaya");
        system.bookAppointment(patient); // Should throw exception
    }

    /**
     * Tests that the appointment details are shown correctly.
     */
    @Test
    public void testAppointmentDetails() {
        system.registerDoctor("DrSushir");
        system.registerPatient("Ram");

        Doctor doctor = system.doctors.get(0);
        doctor.setAvailable(true);

        Patient patient = system.patients.get(0);
        Appointment appointment = system.bookAppointment(patient);

        String expectedDetails = "Appointment: Patient[Ram] - Doctor[DrSushir] - Status: Scheduled";
        assertEquals(expectedDetails, appointment.appointmentDetails());
    }

    /**
     * Tests that appointments can be retrieved using a doctor's ID.
     */
    @Test
    public void testShowAppointmentsByDoctorId() {
        system.registerDoctor("DrSushir");
        system.registerPatient("Ram");

        Doctor doctor = system.doctors.get(0);
        doctor.setAvailable(true);

        Patient patient = system.patients.get(0);
        system.bookAppointment(patient);

        system.showAppointmentsByDoctorId("D1000");
    }

    /**
     * Tests that appointments can be retrieved using a patient's ID.
     */
    @Test
    public void testShowAppointmentsByPatientId() {
        system.registerDoctor("DrSushir");
        system.registerPatient("Ram");

        Doctor doctor = system.doctors.get(0);
        doctor.setAvailable(true);

        Patient patient = system.patients.get(0);
        system.bookAppointment(patient);

        system.showAppointmentsByPatientId("P1000"); // Patient ID should match
    }
}