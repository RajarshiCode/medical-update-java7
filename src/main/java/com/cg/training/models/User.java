package com.cg.training.models;

/**
 * This is an abstract class representing a general User in the system.
 * Both Doctor and Patient classes extend this class.
 * <p>
 * A User has an ID and a name. The name must only contain alphabets and spaces.
 * </p>
 * 
 * @author Rajarshi Das
 */
public abstract class User {

    /** Unique ID for the user. */
    protected String id;

    /** Name of the user. */
    protected String name;

    /**
     * Returns the name of the user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Constructor to create a user with a given ID and name.
     * The name is trimmed and validated to contain only alphabets and spaces.
     *
     * @param id   the unique ID of the user
     * @param name the name of the user (must be alphabetic)
     * @throws IllegalArgumentException if the name is invalid
     */
    public User(String id, String name) {
        name = name.trim(); // Remove leading/trailing spaces
        if (!name.matches("^[A-Za-z||A-Za-z\\s]+")) {
            throw new IllegalArgumentException("Name must contain only alphabets and single spaces between words.");
        }
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the user's ID
     */
    public String getId() {
        return id;
    }

    /**
     * Abstract method to display the profile information of the user.
     * This method must be implemented by subclasses like Doctor or Patient.
     */
    public abstract void showProfile();
}