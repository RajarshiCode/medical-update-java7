package com.cg.training.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * The Security annotation is used to mark a class with a required user role.
 * It can help implement role-based access control in your application.
 * 
 * This annotation can only be applied to types (i.e., classes or interfaces),
 * and it is retained at runtime so it can be accessed via reflection.
 * 
 * Example:
 * <pre>
 * @Security(role = "Admin")
 * public class Admin extends User {
 *     // Only Admins should use this class
 * }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Security {

    /**
     * Specifies the role required to access the annotated class.
     * 
     * @return The required role (e.g., "Admin", "Doctor").
     */
    String role();
}