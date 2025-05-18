package com.cg.training.models;

/**
 * 
 * @author 
 * @param 
 * 
 * this file consists of the attributes for Patient Entity
 * */

public class Patient extends User {
    public Patient(String id, String name) {
        super(id, name);
    }

    @Override
    public void showProfile() {
        System.out.println("Patient ID: " + id + ", Name: " + name);
    }
    
}
