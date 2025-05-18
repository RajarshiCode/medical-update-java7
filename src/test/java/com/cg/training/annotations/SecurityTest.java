package com.cg.training.annotations;

import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class SecurityTest {

    @Security(role = "ADMIN")
    public class TestClass {
    }

    @Test
    public void testSecurityAnnotation() throws NoSuchMethodException {
        // Check if the Security annotation is present on TestClass
        Class<TestClass> clazz = TestClass.class;
        Security securityAnnotation = clazz.getAnnotation(Security.class);
        
        // Assert that the annotation is not null
        assertNotNull("Security annotation should be present", securityAnnotation);
        
        // Assert that the role is as expected
        assertEquals("Role should be ADMIN", "ADMIN", securityAnnotation.role());
    }
}
