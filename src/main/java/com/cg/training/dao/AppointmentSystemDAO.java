package com.cg.training.dao;

import com.cg.training.models.Appointment;
import com.cg.training.models.Patient;

/**
 * AppointmentSystemDAO defines the operations for managing patients,
 * doctors, and appointments in the healthcare appointment system.
 * 
 * This interface includes methods for registration, booking, displaying,
 * and managing appointments and related data.
 * 
 * @author 
 */
public interface AppointmentSystemDAO {

    /**
     * Registers a new patient using the given name.
     * A unique ID will be generated, and the name should be validated.
     * 
     * @param name The name of the patient.
     */
    void registerPatient(String name);

    /**
     * Registers a new doctor using the given name.
     * A unique ID will be generated, and the name should be validated.
     * 
     * @param name The name of the doctor.
     */
    void registerDoctor(String name);

    /**
     * Books an appointment for a given patient with the first available doctor.
     * 
     * @param patient The patient for whom the appointment is to be booked.
     * @return The booked Appointment object.
     */
    Appointment bookAppointment(Patient patient);

    /**
     * Marks the appointment at the specified index as completed.
     * 
     * @param index The index of the appointment in the list.
     */
    void completeAppointment(int index);

    /**
     * Displays the profile of all registered doctors.
     */
    void showAllDoctors();

    /**
     * Displays all the appointments in the system.
     */
    void showAllAppointments();

    /**
     * Displays all appointments for a specific doctor using their ID.
     * 
     * @param doctorId The unique ID of the doctor.
     */
    void showAppointmentsByDoctorId(String doctorId);

    /**
     * Displays all appointments for a specific patient using their ID.
     * 
     * @param patientId The unique ID of the patient.
     */
    void showAppointmentsByPatientId(String patientId);

    /**
     * Finds and returns a patient by their ID.
     * 
     * @param id The unique ID of the patient.
     * @return The Patient object, or null if not found.
     */
    Patient findPatientById(String id);

    /**
     * Saves all appointment details to a file.
     * This method can be used to persist appointment data.
     */
    void saveAppointmentsToFile();

    /**
     * Clears the contents of the appointment file.
     * Typically used before exiting the application to reset the data file.
     */
    void clearAllContentsOfTheFile();
}