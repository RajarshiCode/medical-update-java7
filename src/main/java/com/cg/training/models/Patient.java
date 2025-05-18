package com.cg.training.models;

/**
 * The Patient class represents a patient in the system.
 * It extends the User class and inherits basic user details like ID and name.
 * 
 * This class overrides the showProfile method to display patient-specific details.
 * 
 * @author 
 */
public class Patient extends User {

    /**
     * Constructor to create a new Patient.
     * 
     * @param id   The unique ID of the patient.
     * @param name The name of the patient.
     */
    public Patient(String id, String name) {
        super(id, name);
    }

    /**
     * Displays the patient's profile information.
     * This method overrides the showProfile method from the User class.
     */
    @Override
    public void showProfile() {
        System.out.println("Patient ID: " + id + ", Name: " + name);
    }
}