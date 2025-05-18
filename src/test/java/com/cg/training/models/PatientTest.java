package com.cg.training.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cg.training.models.Patient;

public class PatientTest {

	private Patient patient1;

	@Before
	public void setUp1() {
		patient1 = new Patient("P1001", "Ram");
	}

	@Test
	public void testPatientConstructor_shouldInitializeCorrectly() {
		assertEquals("P1001", patient1.getId());
		assertEquals("Ram", patient1.getName());
	}

	@Test
	public void testShowProfile_shouldNotThrowException() {
		try {
			patient1.showProfile();
		} catch (Exception e) {
			fail("showProfile should not throw any exception");
		}
	}

	private Patient patient;

	@Before
	public void setUp() {
		patient1 = new Patient("P1001", "Ram");
	}

	@Test
	public void testPatient() {
		assertEquals("P1001", patient1.getId());
		assertEquals("Ram", patient1.getName());
	}

	@Test
	public void testShowProfile() {
		try {
			patient1.showProfile();
		} catch (Exception e) {
			fail("showProfile should not throw any exception");
		}
	}
}
