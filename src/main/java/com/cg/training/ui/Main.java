package com.cg.training.ui;

import java.util.Scanner;

import com.cg.training.exceptions.InvalidAppointmentException;
import com.cg.training.models.Admin;
import com.cg.training.models.Patient;
import com.cg.training.service.AppointmentSystem;

/**
 * 
 * 
 * 
 * 
 * 
 * */
public class Main
{
	public static void main(String[] args)  {
        AppointmentSystem system = new AppointmentSystem();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Medical Appointment System");
        System.out.println();
        //Takes input from the user asking the role
        try {
        	while (true) {
                System.out.println("Are you a:");
                System.out.println("1. Doctor\n2. Patient\n3. Exit");
                System.out.print("Choose option (1, 2, or 3): ");
                String roleChoice = sc.nextLine();

                if (roleChoice.equals("1")) {
                   
                    while (true) {
                        System.out.println("\nDoctor Menu:");
                        System.out.println("1. Register a Doctor\n2. Show All Appointments\n3. Complete Appointment\n4. Remove a Doctor\n5. Exit");
                        System.out.print("Enter choice: ");
                        String doctorChoice = sc.nextLine();

                        switch (doctorChoice) {
                            case "1":
                                System.out.print("Enter Doctor Name: ");
                                String dname = sc.nextLine();
                                system.registerDoctor(dname);
                                break;

                            case "2":
                                System.out.print("Enter your Doctor ID (e.g., D1001): ");
                                String docId = sc.nextLine();
                                system.showAppointmentsByDoctorId(docId);
                                break;

                            case "3":
                                system.showAllAppointments();
                                System.out.print("Enter appointment index to complete: ");
                                try {
                                    int index = Integer.parseInt(sc.nextLine());
                                    system.completeAppointment(index);
                                    system.saveAppointmentsToFile();
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid index. Must be a number.");
                                }
                                break;

                            case "4":
                                System.out.print("Enter Doctor ID to remove (e.g., D1001): ");
                                String doctorId = sc.nextLine();
                                Admin admin = new Admin("A1", "Admin");
                                admin.removeDoctor(system.doctors, doctorId);
                                break;

                            case "5":
                                System.out.println("Going back to main menu!!");
                                break;

                            default:
                                System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                                break;
                        }
                        if (doctorChoice.equals("5")) break;
                    }

                } else if (roleChoice.equals("2")) {
                   
                    while (true) {
                        System.out.println("\nPatient Menu:");
                        System.out.println("1. Register a Patient\n2. Show All Doctors\n3. Book Appointment\n4. Show Appointments\n5. Exit");
                        System.out.print("Enter choice: ");
                        String patientChoice = sc.nextLine();

                        switch (patientChoice) {
                            case "1":
                            //Registers a Patient, Calls the functions in the AppointmentSystem Class
                                System.out.print("Enter Patient Name: ");
                                String pname = sc.nextLine();
                                system.registerPatient(pname);
                                break;
                                
                                
                            case "2":{
                            	System.out.println("All Doctors");
                            	system.showAllDoctors();
                            	break;
                            }
                            case "3":
                            //Registers a Doctor, Calls the functions in the AppointmentSystem Class
                                System.out.print("Enter Numeric Patient ID (digits only, without 'P'): ");
                                String pid = sc.nextLine();
                                //Checking weather the Patient ID is valid
                                if (!pid.matches("\\d+")) {
                                    System.out.println("Invalid ID: Must contain only numbers.");
                                    break;
                                }
                                Patient p = system.findPatientById("P" + pid);
                                if (p != null) {
                                    try {
                                        system.bookAppointment(p);
                                        system.saveAppointmentsToFile();
                                    } catch (InvalidAppointmentException e) {
                                        System.out.println("Error: " + e.getMessage());
                                    }
                                } else {
                                    System.out.println("Patient not found.");
                                }
                                break;

                            case "4":
                            //Shows appointment of patients, Calls the functions in the AppointmentSystem Class
                                System.out.print("Enter your Patient ID (e.g., P1001): ");
                                String patId = sc.nextLine();
                                system.showAppointmentsByPatientId(patId);
                                break;

                            case "5":
                            //Return Back to previous menu
                                System.out.println("Going back to main menu!!");
                                break;

                            default:
                                System.out.println("Invalid choice. Please enter a number from 1 to 4.");
                        }

                        if (patientChoice.equals("5")) break;
                    }

                }else if(roleChoice.equals("3")) {
                	System.out.println("Exiting......, Thank You!");
                	system.clearContainsOfTheFile();
                	sc.close();
                	System.exit(0);            
                }
                else 
                    System.out.println("Invalid role choice. Please enter 1 or 2.");
                    
                
            }
        }catch(Exception e) {
        	
        }
        
    }
}
