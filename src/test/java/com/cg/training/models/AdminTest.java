package com.cg.training.models;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains tests for the Admin functionality. It checks how the Admin 
 * interacts with the list of doctors, and ensures that everything works as expected.
 * It also tests the availability and profile of doctors.
 * 
 * @author Sampritee Dey
 */
public class AdminTest {

    private Admin admin; // Admin object for testing
    private List<Doctor> doctors; // List to store doctors

    /**
     * This method runs before each test. It sets up the Admin object and a list of doctors.
     * It prepares the data so each test can run with a fresh set of objects.
     * 
     * @author Sampritee Dey
     */
    @Before
    public void setUp() {
        admin = new Admin("A1", "AdminName"); // Create Admin with ID "A1" and name "AdminName"
        doctors = new ArrayList<>(); // Create an empty list of doctors

        // Add a few doctors to the list
        doctors.add(new Doctor("D1001", "Ram"));
        doctors.add(new Doctor("D1002", "Laxman"));
        doctors.add(new Doctor("D1003", "Bharat"));
    }

    /**
     * Test case for removing a doctor from the list by their ID.
     * This test checks if the doctor is successfully removed when given a valid ID.
     * 
     * @author Sampritee Dey
     */
    @Test
    public void testRemoveDoctor_ValidId() {
        admin.removeDoctor(doctors, "D1002"); // Try to remove doctor with ID "D1002"

        // After removing, the list should have only 2 doctors left
        assertEquals(2, doctors.size());

        // Check if the doctor with ID "D1002" is no longer in the list
        assertFalse(doctors.stream().anyMatch(d -> d.getId().equals("D1002")));
    }

    /**
     * Test case for removing a doctor from the list with an invalid ID.
     * This test checks that nothing happens if the ID is not found.
     * 
     * @author Sampritee Dey
     */
    @Test
    public void testRemoveDoctor_InvalidId() {
        admin.removeDoctor(doctors, "D9999"); // Try to remove doctor with an invalid ID "D9999"

        // The list should still have 3 doctors because the ID was invalid
        assertEquals(3, doctors.size());
    }

    /**
     * Test case for showing the profile of the Admin.
     * This test checks if the Admin's details (ID and name) are printed correctly.
     * 
     * @author Sampritee Dey
     */
    @Test
    public void testShowProfile() {
        admin.showProfile(); // Print out Admin's profile (ID and name)
    }

    /**
     * Test case for checking the availability of a doctor.
     * This test checks if a doctor is available and if we can change their availability.
     * 
     * @author Sampritee Dey
     */
    @Test
    public void testDoctorAvailability() {
        Doctor d = new Doctor("D2001", "Hanuman"); // Create a new doctor

        // Initially, the doctor should be available
        assertTrue(d.isAvailable());

        // Change the doctor's availability to false (not available)
        d.setAvailable(false);

        // Check that the doctor is now marked as unavailable
        assertFalse(d.isAvailable());
    }

    /**
     * Test case for showing the profile of a doctor.
     * This test checks if the doctor's details (ID, name, and availability) are printed correctly.
     * 
     * @author Sampritee Dey
     */
    @Test
    public void testDoctorShowProfile() {
        Doctor d = new Doctor("D2002", "Sita"); // Create a new doctor with ID "D2002"
        
        // Print out the doctor's profile (ID, name, and availability)
        d.showProfile();
    }

    /**
     * Test case to check the integrity of the list of doctors.
     * This test ensures the list of doctors contains the correct IDs and names.
     * 
     * @author Sampritee Dey
     */
    @Test
    public void testDoctorListIntegrity() {
        // Verify that the doctors in the list have the correct IDs and names
        assertEquals("D1001", doctors.get(0).getId()); // Check ID of the first doctor
        assertEquals("D1002", doctors.get(1).getId()); // Check ID of the second doctor
        assertEquals("D1003", doctors.get(2).getId()); // Check ID of the third doctor

        assertEquals("Ram", doctors.get(0).getName()); // Check name of the first doctor
        assertEquals("Laxman", doctors.get(1).getName()); // Check name of the second doctor
        assertEquals("Bharat", doctors.get(2).getName()); // Check name of the third doctor
    }

    /**
     * Test case to check the Admin's getters for ID and name.
     * This test ensures that the Admin's ID and name are correct.
     * 
     * @author Sampritee Dey
     */
    @Test
    public void testAdminGetters() {
        // Verify that the Admin's ID and name match the expected values
        assertEquals("A1", admin.getId()); // Check Admin's ID
        assertEquals("AdminName", admin.getName()); // Check Admin's name
    }

    /**
     * Edge case test to remove a doctor when the list is empty.
     * This test ensures that nothing breaks if you try to remove a doctor from an empty list.
     * 
     * @author Sampritee Dey
     */
    @Test
    public void testRemoveDoctor_EmptyList() {
        List<Doctor> emptyDoctorsList = new ArrayList<>(); // Create an empty list of doctors
        
        // Try to remove a doctor from the empty list
        admin.removeDoctor(emptyDoctorsList, "D1002");

        // The list should still be empty after the removal attempt
        assertTrue(emptyDoctorsList.isEmpty());
    }
    
    /**
     * Edge case test to remove a doctor with a null ID.
     * This test ensures that nothing breaks if you try to remove a doctor with a null ID.
     * 
     * @author 
     */
    @Test
    public void testRemoveDoctor_NullId() {
        // Try to remove a doctor with a null ID
        admin.removeDoctor(doctors, null);

        // The list should remain unchanged, still containing 3 doctors
        assertEquals(3, doctors.size());
    }
}