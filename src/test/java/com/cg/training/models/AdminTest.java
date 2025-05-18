package com.cg.training.models;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

public class AdminTest {

	private Admin admin;
	private List<Doctor> doctors;

	@Before
	public void setUp() {
		admin = new Admin("A1", "AdminName");
		doctors = new ArrayList<>();
		doctors.add(new Doctor("D1001", "Ram"));
		doctors.add(new Doctor("D1002", "Laxman"));
		doctors.add(new Doctor("D1003", "Bharat"));
	}

	@Test
	public void testRemoveDoctor_ValidId() {
		admin.removeDoctor(doctors, "D1002");

		assertEquals(2, doctors.size());
		assertFalse(doctors.stream().anyMatch(d -> d.getId().equals("D1002")));
	}

	@Test
	public void testRemoveDoctor_InvalidId() {
		admin.removeDoctor(doctors, "D9999");

		assertEquals(3, doctors.size());
	}

	@Test
	public void testShowProfile() {

		admin.showProfile();
	}

	@Test
	public void testDoctorAvailability() {
		Doctor d = new Doctor("D2001", "Hanuman");

		assertTrue(d.isAvailable());

		d.setAvailable(false);
		assertFalse(d.isAvailable());
	}

	@Test
	public void testDoctorShowProfile() {
		Doctor d = new Doctor("D2002", "Sita");
		d.showProfile();
	}

	@Test
	public void testDoctorListIntegrity() {

		assertEquals("D1001", doctors.get(0).getId());
		assertEquals("D1002", doctors.get(1).getId());
		assertEquals("D1003", doctors.get(2).getId());

		assertEquals("Ram", doctors.get(0).getName());
		assertEquals("Laxman", doctors.get(1).getName());
		assertEquals("Bharat", doctors.get(2).getName());
	}

	@Test
	public void testAdminGetters() {

		assertEquals("A1", admin.getId());
		assertEquals("AdminName", admin.getName());
	}
}