package com.cg.training.models;

import java.util.List;

import com.cg.training.annotations.Security;

/**
 * The Admin class represents an administrative user in the system.
 * It extends the User class and adds additional functionality
 * specific to admin users.
 * 
 * This class is annotated with @Security to indicate that only
 * users with the "Admin" role can access it.
 */
@Security(role = "Admin")
public class Admin extends User {

    /**
     * Constructor for the Admin class.
     * 
     * @param id   The unique ID of the admin.
     * @param name The name of the admin.
     */
    public Admin(String id, String name) {
        super(id, name);
    }

    /**
     * Displays the admin's profile information.
     * This method overrides the showProfile method from the User class.
     */
    @Override
    public void showProfile() {
        System.out.println("Admin ID: " + id + ", Name: " + name);
    }

    /**
     * Removes a doctor from the list based on the given doctor ID.
     * 
     * @param doctors  The list of doctors.
     * @param doctorId The ID of the doctor to be removed.
     */
    public void removeDoctor(List<Doctor> doctors, String doctorId) {
        if (doctors.size() == 0) {
            System.out.println("No doctor in the list");
        } else {
            for (int i = 0; i < doctors.size(); i++) {
                Doctor d = doctors.get(i);
                if (d.getId().equals(doctorId)) {
                    doctors.remove(i);
                    System.out.println("Doctor removed.");
                    return;
                }
            }
            System.out.println("Doctor ID not found.");
        }
    }
}