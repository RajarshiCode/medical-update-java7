package com.cg.training.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.cg.training.dao.AppointmentSystemDAO;
import com.cg.training.exceptions.InvalidAppointmentException;
import com.cg.training.models.Appointment;
import com.cg.training.models.Doctor;
import com.cg.training.models.Patient;

public class AppointmentSystem implements AppointmentSystemDAO {
	/** List to store all registered patients **/
	public List<Patient> patients;
	/** List to store all registered doctors **/
	public List<Doctor> doctors;
	/** List to store all booked appointments **/
	public List<Appointment> appointments;
	/** Counter to generate unique patient IDs **/
	int patientCounter = 1000;
	/** Counter to generate unique doctor IDs **/
	int doctorCounter = 1000;
	
	/**
	 * Constructor initializes the lists for patients, doctors, and appointments
	 **/
	public AppointmentSystem() {
		patients = new ArrayList<Patient>();
		doctors = new ArrayList<Doctor>();
		appointments = new ArrayList<Appointment>();
	}

	/** Registers a new patient with a unique ID and validates the name. **/
	@Override
	public void registerPatient(String name) {
		try {
			String id = "P" + patientCounter++;
			patients.add(new Patient(id, name));
			System.out.println("Patient registered with ID: " + id);
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/** Registers a new doctor with a unique ID and validates the name. **/
	@Override
	public void registerDoctor(String name) {
		try {
			String id = "D" + doctorCounter++;
			doctors.add(new Doctor(id, name));
			System.out.println("Doctor registered with ID: " + id);
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Books an appointment for a given patient with the first available doctor.
	 **/
	@Override
	public Appointment bookAppointment(Patient patient) {
		try (BufferedWriter write = new BufferedWriter(new FileWriter("D:\\filesCreating\\Appointments.csv", true))) {
			for (Doctor doctor : doctors) {
				if (doctor.isAvailable()) {
					Appointment appointment = new Appointment(patient, doctor);
					appointments.add(appointment);
//					write.write(appointment.appointmentDetails());
//					write.newLine();
//					write.close();
					System.out.println("Appointment booked.");
					return appointment;
				}
			}
		} catch (InvalidAppointmentException e) {
			System.out.println("Error: No available doctor found.");
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
		return null;
	}

	@Override
	public void saveAppointmentsToFile() {
		try (BufferedWriter write = new BufferedWriter(new FileWriter("D:\\filesCreating\\Appointments.csv", true))) {
			for (int i = 0; i < appointments.size(); i++) {
				write.write("Appointment Index: " + i + ": " + appointments.get(i).appointmentDetails());
				write.newLine();
				write.close();
			}
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	/**
	 * To mark the Appointments as completed according to the index given by the
	 * user
	 **/
	@Override
	public void completeAppointment(int index) {
		try {
			if (index < 0 || index >= appointments.size()) {
				throw new InvalidAppointmentException("Invalid appointment index.");
			}
			Appointment appointment = appointments.get(index);
			if ("Completed".equals(appointment.getStatus())) {
				throw new InvalidAppointmentException("Appointment already completed.");
			}
			appointment.completeAppointment();
			System.out.println("Appointment marked completed.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/** Displaying all the profile of the doctors **/
	@Override
	public void showAllDoctors() {
		for (Doctor d : doctors) {
			d.showProfile();
		}
	}

	/** Displaying all the appointments **/
	@Override
	public void showAllAppointments() {
		for (int i = 0; i < appointments.size(); i++) {
			System.out.println(i + ": " + appointments.get(i).appointmentDetails());
		}
	}

	/** Helps in showing all the appointments with the help of doctorID **/
	@Override
	public void showAppointmentsByDoctorId(String doctorId) {
		boolean found = false;
		for (int i = 0; i < appointments.size(); i++) {
			Appointment a = appointments.get(i);
			if (a.doctor.getId().equals(doctorId)) {
				System.out.println("Appointment Index: " + i + ": " + a.appointmentDetails());
				found = true;
			}
		}
		if (!found) {
			System.out.println("No appointments found for Doctor ID: " + doctorId);
		}
	}

	/** Helps in showing all the appointments of the patients using patientsID **/
	@Override
	public void showAppointmentsByPatientId(String patientId) {
		boolean found = false;
		for (int i = 0; i < appointments.size(); i++) {
			Appointment a = appointments.get(i);
			if (a.patient.getId().equals(patientId)) {
				System.out.println("Appointment Index: " + i + ": " + a.appointmentDetails());
				found = true;
			}
		}
		if (!found) {
			System.out.println("No appointments found for Patient ID: " + patientId);
		}
	}

	@Override
	public Patient findPatientById(String id) {
		for (Patient p : patients) {
			if (p.getId().equals(id)) {
				return p;
			}
		}
		return null;
	}

}
