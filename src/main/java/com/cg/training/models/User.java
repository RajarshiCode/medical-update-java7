package com.cg.training.models;

public abstract class User {
    protected String id;
    protected String name;
    
    public String getName() {
		return name;
	}

	public User(String id, String name) {
        name = name.trim(); // remove leading/trailing spaces
        if (!name.matches("^[A-Za-z||A-Za-z\\s]+")) {
            throw new IllegalArgumentException("Name must contain only alphabets and single spaces between words.");
        }
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public abstract void showProfile();
}


