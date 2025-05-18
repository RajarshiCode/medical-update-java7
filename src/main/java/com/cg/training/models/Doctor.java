package com.cg.training.models;

/**
 * The Doctor class represents a doctor in the system.
 * It extends the User class and adds specific functionality 
 * such as availability status.
 */
public class Doctor extends User {

    /**
     * Indicates whether the doctor is available for appointments.
     * Default is true (available).
     */
    private boolean available = true;

    /**
     * Constructor to create a new Doctor.
     * 
     * @param id   The unique ID of the doctor.
     * @param name The name of the doctor.
     */
    public Doctor(String id, String name) {
        super(id, name);
    }

    /**
     * Checks if the doctor is available.
     * 
     * @return true if available, false if not.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the availability of the doctor.
     * 
     * @param available true if the doctor should be marked available, false otherwise.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Displays the doctor's profile, including ID, name, and availability status.
     * This method overrides the showProfile method from the User class.
     */
    @Override
    public void showProfile() {
        System.out.println("Doctor ID: " + id + ", Name: " + name + ", Available: " + available);
    }
}