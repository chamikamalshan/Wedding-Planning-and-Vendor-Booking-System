package com.wedding.model;

public abstract class Vendor extends Person {
    protected String serviceType;
    protected double price;

    public Vendor(String id, String name, String phone, String serviceType, double price) {
        super(id, name, phone);
        this.serviceType = serviceType;
        this.price = price;
    }
    public abstract String getCategory();
    // getters/setters
}