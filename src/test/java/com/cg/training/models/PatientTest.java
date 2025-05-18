package com.cg.training.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the Patient class to ensure that patient data
 * is correctly initialized and the showProfile method works without errors.
 * <p>
 * It is written in a simple format for beginners to understand.
 * </p>
 * 
 * @author Sampritee Dey
 */
public class PatientTest {

    private Patient patient;

    /**
     * This method runs before each test and creates a Patient object
     * with sample data to be used in testing.
     */
    @Before
    public void setUp() {
        patient = new Patient("P1001", "Ram");
    }

    /**
     * This test checks that the Patient constructor initializes
     * the ID and name correctly.
     */
    @Test
    public void testPatientConstructor_shouldInitializeCorrectly() {
        assertEquals("P1001", patient.getId());
        assertEquals("Ram", patient.getName());
    }

    /**
     * This test ensures that the showProfile method runs
     * without throwing any exceptions.
     */
    @Test
    public void testShowProfile_shouldNotThrowException() {
        try {
            patient.showProfile();
        } catch (Exception e) {
            fail("showProfile should not throw any exception");
        }
    }
}