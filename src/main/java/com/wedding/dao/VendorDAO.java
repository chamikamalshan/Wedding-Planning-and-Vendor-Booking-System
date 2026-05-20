package com.wedding.dao;

import com.wedding.model.PremiumVendor;
import com.wedding.model.StandardVendor;
import com.wedding.model.Vendor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Vendors.
 */
public class VendorDAO {

    private static final String DELIMITER = "\\|";   // for split (regex)
    private static final String JOINER    = "|";     // for writing
    private final String filePath;

    public VendorDAO(String filePath) {
        this.filePath = filePath;
        ensureFileExists();
    }

    private void ensureFileExists() {
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialise data file: " + filePath, e);
        }
    }

    /**
     * CREATE : Append a new vendor to the file.
     */
    public void addVendor(Vendor vendor) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(toLine(vendor));
            writer.newLine();
        }
    }

    /**
     * READ : Read all vendors from the file.
     */
    public List<Vendor> getAllVendors() throws IOException {
        List<Vendor> vendors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Vendor v = fromLine(line);
                if (v != null) vendors.add(v);
            }
        }
        return vendors;
    }

    /**
     * READ : Look up a vendor by id. Returns null if not found.
     */
    public Vendor getVendorById(String id) throws IOException {
        for (Vendor v : getAllVendors()) {
            if (v.getId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Search vendors by a keyword.
     */
    public List<Vendor> searchVendors(String keyword) throws IOException {
        List<Vendor> result = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllVendors();
        }
        String k = keyword.trim().toLowerCase();
        for (Vendor v : getAllVendors()) {
            if (v.getName().toLowerCase().contains(k)
                    || v.getServiceType().toLowerCase().contains(k)
                    || v.getCompany().toLowerCase().contains(k)) {
                result.add(v);
            }
        }
        return result;
    }

    /**
     * UPDATE : Update a vendor by id. Returns true if a vendor was updated.
     */
    public boolean updateVendor(Vendor updated) throws IOException {
        List<Vendor> all = getAllVendors();
        boolean found = false;
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId().equalsIgnoreCase(updated.getId())) {
                all.set(i, updated);
                found = true;
                break;
            }
        }
        if (found) {
            rewriteFile(all);
        }
        return found;
    }

    /**
     * DELETE : Delete a vendor by id.
     */
    public boolean deleteVendor(String id) throws IOException {
        List<Vendor> all = getAllVendors();
        boolean removed = all.removeIf(v -> v.getId().equalsIgnoreCase(id));
        if (removed) {
            rewriteFile(all);
        }
        return removed;
    }

    /**
     * Helpers
     */

    private void rewriteFile(List<Vendor> vendors) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Vendor v : vendors) {
                writer.write(toLine(v));
                writer.newLine();
            }
        }
    }

    //Convert a Vendor object into a single line of text.
    private String toLine(Vendor v) {
        return String.join(JOINER,
                safe(v.getId()),
                safe(v.getName()),
                safe(v.getEmail()),
                safe(v.getPhone()),
                safe(v.getServiceType()),
                safe(v.getCompany()),
                String.valueOf(v.getBasePrice()),
                safe(v.getAvailability()),
                safe(v.getCategory()));
    }

    //Convert a single line of text back into a Vendor object.
    private Vendor fromLine(String line) {
        String[] p = line.split(DELIMITER, -1);
        if (p.length < 9) return null;
        try {
            String id           = p[0].trim();
            String name         = p[1].trim();
            String email        = p[2].trim();
            String phone        = p[3].trim();
            String serviceType  = p[4].trim();
            String company      = p[5].trim();
            double basePrice    = Double.parseDouble(p[6].trim());
            String availability = p[7].trim();
            String category     = p[8].trim();

            if ("Premium".equalsIgnoreCase(category)) {
                return new PremiumVendor(id, name, email, phone,
                        serviceType, company, basePrice, availability);
            } else {
                return new StandardVendor(id, name, email, phone,
                        serviceType, company, basePrice, availability);
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String safe(String s) {
        if (s == null) return "";
        // Avoid breaking the line format if user types our delimiter
        return s.replace("|", "/");
    }
}
