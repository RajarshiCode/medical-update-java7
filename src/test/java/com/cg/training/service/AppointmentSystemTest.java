package com.cg.training.service;

import com.cg.training.exceptions.InvalidAppointmentException;
import com.cg.training.models.Appointment;
import com.cg.training.models.Doctor;
import com.cg.training.models.Patient;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class AppointmentSystemTest {
	private AppointmentSystem system;

	@Before
	public void setUp() {
		system = new AppointmentSystem();
	}

	@Test
	public void testRegisterPatient() {
		system.registerPatient("Ram");
		Patient patient = system.findPatientById("P1000");
		assertNotNull(patient);
		assertEquals("P1000", patient.getId());
		assertEquals("Ram", patient.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRegisterInvalidPatient() {
		system.registerPatient("123Ram");
	}

	@Test
	public void testRegisterDoctor() {
		system.registerDoctor("DrSushir");
		List<Doctor> doctors = system.doctors;
		assertEquals(1, doctors.size());
		assertEquals("D1000", doctors.get(0).getId());
	}

	// Test for invalid doctor registration (non-alphabetic name)
	@Test(expected = IllegalArgumentException.class)
	public void testRegisterInvalidDoctor() {
		system.registerDoctor("Dr123");
	}

	// Test booking an appointment
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

	// Test booking appointment with no available doctor
	@Test(expected = InvalidAppointmentException.class)
	public void testBookAppointmentwithnoDoctor() {
		system.registerDoctor("DrSushir");
		Doctor doctor = system.doctors.get(0);
		doctor.setAvailable(false);

		Patient patient = new Patient("P2001", "Sham");
		system.bookAppointment(patient);
	}

	// Test completing an appointment
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

	@Test(expected = InvalidAppointmentException.class)
	public void testCompleteAppointmentinvalidIndex() throws InvalidAppointmentException {
		system.completeAppointment(5);
	}

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

	// Test booking an appointment when all doctors are unavailable
	@Test(expected = InvalidAppointmentException.class)
	public void testBookAppointmentWithNoAvailableDoctor() {
		system.registerDoctor("DrSushir");
		Doctor doctor = system.doctors.get(0);
		doctor.setAvailable(false); // Doctor unavailable

		Patient patient = new Patient("P2002", "Jaya");
		system.bookAppointment(patient); // Should throw exception
	}

	// Test appointment details
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

	// Test retrieving appointments by doctor ID
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
