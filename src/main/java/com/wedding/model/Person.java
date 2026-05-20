package com.wedding.model;

/**
 * Abstract base class demonstrating ABSTRACTION and ENCAPSULATION.
 * All people in the system (vendors, customers, etc.) share these basic attributes.
 */
public abstract class Person {

    // ENCAPSULATION: private fields, accessed only through getters/setters
    private String id;
    private String name;
    private String email;
    private String phone;

    public Person() {
    }

    public Person(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // ABSTRACTION: subclasses must define how they describe themselves
    public abstract String getRole();

    // Getters and setters (encapsulation)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
