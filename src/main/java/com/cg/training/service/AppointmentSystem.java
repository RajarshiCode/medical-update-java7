package com.cg.training.service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cg.training.dao.AppointmentSystemDAO;
import com.cg.training.exceptions.InvalidAppointmentException;
import com.cg.training.models.Appointment;
import com.cg.training.models.Doctor;
import com.cg.training.models.Patient;

/**
 * This class handles the main functionality of the appointment system.
 * It manages the registration of patients and doctors, booking appointments,
 * and tracking appointment status.
 * 
 * Author: Shrestha Das
 */
public class AppointmentSystem implements AppointmentSystemDAO {

    /** List to store all registered patients. */
    public List<Patient> patients;

    /** List to store all registered doctors. */
    public List<Doctor> doctors;

    /** List to store all booked appointments. */
    public List<Appointment> appointments;

    /** Counter to generate unique patient IDs. */
    int patientCounter = 1000;

    /** Counter to generate unique doctor IDs. */
    int doctorCounter = 1000;

    /**
     * Constructor to initialize the lists for patients, doctors, and appointments.
     */
    public AppointmentSystem() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    /**
     * Registers a new patient with a unique ID.
     * Validates the name and adds the patient to the list.
     *
     * @param name the name of the patient
     */
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

    /**
     * Registers a new doctor with a unique ID.
     * Validates the name and adds the doctor to the list.
     *
     * @param name the name of the doctor
     */
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
     *
     * @param patient the patient who wants to book the appointment
     * @return the booked Appointment object, or null if no doctor is available
     */
    @Override
    public Appointment bookAppointment(Patient patient) {
        try {
            for (Doctor doctor : doctors) {
                if (doctor.isAvailable()) {
                    Appointment appointment = new Appointment(patient, doctor);
                    appointments.add(appointment);
                    System.out.println("Appointment booked.");
                    return appointment;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Saves all the appointment details to a CSV file.
     */
    @Override
    public void saveAppointmentsToFile() {
        try (BufferedWriter write = new BufferedWriter(new FileWriter("D:\\filesCreating\\Appointments.csv", true))) {
            for (int i = 0; i < appointments.size(); i++) {
                write.write("Appointment Index: " + i + ": " + appointments.get(i).appointmentDetails());
                write.newLine();
            }
            System.out.println("Appointments saved to file.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Marks an appointment as completed using its index in the list.
     *
     * @param index the index of the appointment to complete
     */
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

    /**
     * Displays the profiles of all registered doctors.
     */
    @Override
    public void showAllDoctors() {
    	if(doctors.size()==0)
    		System.out.println("No registered doctors");    	
    	else {
    		System.out.println(".....All Doctors.....");
    		for (Doctor d : doctors) {
                d.showProfile();
            }
    	}
    }

    /**
     * Displays details of all appointments.
     */
    @Override
    public void showAllAppointments() {
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(i + ": " + appointments.get(i).appointmentDetails());
        }
    }

    /**
     * Displays all appointments for a given doctor ID.
     *
     * @param doctorId the ID of the doctor
     */
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

    /**
     * Displays all appointments for a given patient ID.
     *
     * @param patientId the ID of the patient
     */
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

    /**
     * Clears the contents of the Appointments CSV file.
     */
    @Override
    public void clearAllContentsOfTheFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\filesCreating\\Appointments.csv");
            fileOutputStream.close();
            System.out.println("File contents cleared successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while clearing the file: " + e.getMessage());
        }
    }

    /**
     * Finds and returns a patient by their ID.
     *
     * @param id the ID of the patient to find
     * @return the Patient object if found, otherwise null
     */
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