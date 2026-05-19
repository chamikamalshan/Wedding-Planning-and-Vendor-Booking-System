package com.wedding.model;

public class StandardVendor extends Vendor {
    public StandardVendor(String id, String name, String phone, String serviceType, double price) {
        super(id, name, phone, serviceType, price);
    }
    @Override
    public String getCategory() { return "Standard"; }
}