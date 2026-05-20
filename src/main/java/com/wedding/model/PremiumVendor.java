package com.wedding.model;

/**
 * Premium vendors charge a higher rate that includes a premium surcharge,
 */
public class PremiumVendor extends Vendor {

    private static final double PREMIUM_SURCHARGE_RATE = 0.20; // 20% premium
    private static final double SERVICE_TAX_RATE = 0.05;       // 5% tax

    public PremiumVendor() {
        super();
    }

    public PremiumVendor(String id, String name, String email, String phone,
                         String serviceType, String company,
                         double basePrice, String availability) {
        super(id, name, email, phone, serviceType, company, basePrice, availability);
    }

    /**
     * Overrides Vendor.getServiceFee() with premium surcharge + tax.
     * Final fee = basePrice * (1 + 20% premium) * (1 + 5% tax)
     */
    @Override
    public double getServiceFee() {
        return getBasePrice() * (1 + PREMIUM_SURCHARGE_RATE) * (1 + SERVICE_TAX_RATE);
    }

    @Override
    public String getCategory() {
        return "Premium";
    }
}
