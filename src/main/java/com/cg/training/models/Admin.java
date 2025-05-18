package com.cg.training.models;

import java.util.List;

import com.cg.training.annotations.Security;

@Security(role = "Admin")
public class Admin extends User {
    public Admin(String id, String name) {
        super(id, name);
    }
    
    

    @Override
    public void showProfile() {
        System.out.println("Admin ID: " + id + ", Name: " + name);
    }

    public void removeDoctor(List<Doctor> doctors, String doctorId) {
    	if(doctors.size()==0)
    		System.out.println("No doctor in the list");
    	else {
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