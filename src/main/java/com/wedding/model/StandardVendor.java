package com.wedding.model;

/**
 * A standard-tier vendor charges only the base price plus a small service tax.
 */
public class StandardVendor extends Vendor {

    private static final double SERVICE_TAX_RATE = 0.05; // 5%

    public StandardVendor() {
        super();
    }

    public StandardVendor(String id, String name, String email, String phone,
                          String serviceType, String company,
                          double basePrice, String availability) {
        super(id, name, email, phone, serviceType, company, basePrice, availability);
    }

    /**
     * overrides Vendor.getServiceFee() with a 5% service tax.
     */
    @Override
    public double getServiceFee() {
        return getBasePrice() * (1 + SERVICE_TAX_RATE);
    }

    @Override
    public String getCategory() {
        return "Standard";
    }
}
