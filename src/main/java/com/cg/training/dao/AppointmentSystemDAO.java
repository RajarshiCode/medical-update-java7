package com.cg.training.dao;

import com.cg.training.models.Appointment;
import com.cg.training.models.Patient;

public interface AppointmentSystemDAO {

	/** Registers a new patient with a unique ID and validates the name. **/
	void registerPatient(String name);

	/** Registers a new doctor with a unique ID and validates the name. **/
	void registerDoctor(String name);

	/**Books an appointment for a given patient with the first available doctor.**/
	Appointment bookAppointment(Patient patient);

	/**
	 * To mark the Appointments as completed according to the index given by the
	 * user
	 **/
	void completeAppointment(int index);

	/** Displaying all the profile of the doctors **/
	void showAllDoctors();

	/** Displaying all the appointments **/
	void showAllAppointments();

	/** Helps in showing all the appointments with the help of doctorID **/
	void showAppointmentsByDoctorId(String doctorId);

	/** Helps in showing all the appointments of the patients using patientsID **/
	void showAppointmentsByPatientId(String patientId);

	Patient findPatientById(String id);
	/**
	 * helps in saving the details of all the appointments by the patient
	 * */
	void saveAppointmentsToFile();
	
	/**
	 * this method clears the contents of the file before exiting the Main
	 * 
	 * */
	void clearContainsOfTheFile();
	
	
}
