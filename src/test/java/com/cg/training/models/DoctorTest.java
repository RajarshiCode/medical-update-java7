package com.cg.training.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class DoctorTest {

	private Doctor doctor;

	@Before
	public void setUp() {
		doctor = new Doctor("D1001", "DrSushir");
	}

	@Test
	public void testDoctor() {
		assertEquals("D1001", doctor.getId());
		assertEquals("DrSushir", doctor.getName());
		assertTrue(doctor.isAvailable());
	}

	@Test
	public void testisAvailable() {
		doctor.setAvailable(false);
		assertFalse(doctor.isAvailable());

		doctor.setAvailable(true);
		assertTrue(doctor.isAvailable());
	}

	@Test
	public void testShowProfile() {
		try {
			doctor.showProfile();
		} catch (Exception e) {
		}
	}

}
