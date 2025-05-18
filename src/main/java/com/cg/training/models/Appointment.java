package com.cg.training.models;



public class Appointment {
	public Patient patient;
	public Doctor doctor;
	String status = "Scheduled";

	public Appointment(Patient patient, Doctor doctor) {
		this.patient = patient;
		this.doctor = doctor;
		doctor.setAvailable(false);
	}

	public void completeAppointment() {
		status = "Completed";
		doctor.setAvailable(true);
	}

	public String getStatus() {
		return status;
	}

	public String appointmentDetails() {
		return "Appointment: Patient[" + patient.name + "] - Doctor[" + doctor.name + "] - Status: " + status;
	}
}