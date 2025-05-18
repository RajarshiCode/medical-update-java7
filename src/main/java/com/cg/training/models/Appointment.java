package com.cg.training.models;

/**
 * The Appointment class represents an appointment between a patient and a doctor.
 * It includes details such as the patient, the doctor, and the appointment status.
 */
public class Appointment {

    /** The patient involved in the appointment. */
    public Patient patient;

    /** The doctor involved in the appointment. */
    public Doctor doctor;

    /** The current status of the appointment. Default is "Scheduled". */
    String status = "Scheduled";

    /**
     * Constructor to create a new appointment.
     * When an appointment is created, the doctor's availability is set to false.
     * 
     * @param patient The patient attending the appointment.
     * @param doctor  The doctor assigned to the appointment.
     */
    public Appointment(Patient patient, Doctor doctor) {
        this.patient = patient;
        this.doctor = doctor;
        doctor.setAvailable(false); // Mark doctor as unavailable
    }

    /**
     * Marks the appointment as completed and makes the doctor available again.
     */
    public void completeAppointment() {
        status = "Completed";
        doctor.setAvailable(true); // Doctor is now available for another appointment
    }

    /**
     * Gets the current status of the appointment.
     * 
     * @return The appointment status ("Scheduled" or "Completed").
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns a string with details about the appointment.
     * 
     * @return A formatted string showing patient name, doctor name, and status.
     */
    public String appointmentDetails() {
        return "Appointment: Patient[" + patient.name + "] - Doctor[" + doctor.name + "] - Status: " + status;
    }
}