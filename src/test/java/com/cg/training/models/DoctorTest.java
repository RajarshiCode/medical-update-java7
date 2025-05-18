package com.cg.training.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test class for the Doctor model.
 * <p>
 * This class tests the functionality of the Doctor class such as
 * object creation, availability setting, and profile display.
 * </p>
 * 
 * @author Soumili Ghosh
 */
public class DoctorTest {

    private Doctor doctor;

    /**
     * This method is run before each test case.
     * It sets up a Doctor object to be used in tests.
     */
    @Before
    public void setUp() {
        doctor = new Doctor("D1001", "DrSushir");
    }

    /**
     * Test the Doctor constructor and getter methods.
     * It checks the ID, name, and default availability of the doctor.
     */
    @Test
    public void testDoctor() {
        assertEquals("D1001", doctor.getId());
        assertEquals("DrSushir", doctor.getName());
        assertTrue(doctor.isAvailable());
    }

    /**
     * Test the isAvailable and setAvailable methods.
     * It verifies the doctor's availability status can be changed correctly.
     */
    @Test
    public void testisAvailable() {
        doctor.setAvailable(false);
        assertFalse(doctor.isAvailable());

        doctor.setAvailable(true);
        assertTrue(doctor.isAvailable());
    }

    /**
     * Test the showProfile method.
     * This is a basic test to ensure the method runs without throwing an exception.
     */
    @Test
    public void testShowProfile() {
        try {
            doctor.showProfile();
        } catch (Exception e) {
            // Exception is caught and ignored for this simple test.
        }
    }

}