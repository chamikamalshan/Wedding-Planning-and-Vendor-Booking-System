package com.wedding.model;

public class PremiumVendor extends Vendor {
    public PremiumVendor(String id, String name, String phone, String serviceType, double price) {
        super(id, name, phone, serviceType, price);
    }
    @Override
    public String getCategory() { return "Premium"; }
}