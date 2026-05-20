package com.wedding.model;

/**
 * Vendor class demonstrating INHERITANCE (extends Person)
 * and ENCAPSULATION (private fields with accessors).
 */
public class Vendor extends Person {

    private String serviceType;
    private String company;
    private double basePrice;
    private String availability;
    public Vendor() {
        super();
    }

    public Vendor(String id, String name, String email, String phone,
                  String serviceType, String company,
                  double basePrice, String availability) {
        super(id, name, email, phone);
        this.serviceType = serviceType;
        this.company = company;
        this.basePrice = basePrice;
        this.availability = availability;
    }

    public double getServiceFee() {
        return basePrice;
    }

    @Override
    public String getRole() {
        return "Vendor";
    }

    // Encapsulation: getters and setters
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getCategory() {
        return "Standard";
    }
}
