package com.wedding.dao;

import com.wedding.model.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;

public class VendorDAO {
    private static final String FILE_PATH =
            "src/main/webapp/WEB-INF/data/vendors.txt";

    public List<Vendor> getAllVendors() throws IOException {
        List<Vendor> vendors = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[5].equals("Standard"))
                vendors.add(new StandardVendor(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4])));
            else
                vendors.add(new PremiumVendor(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4])));
        }
        return vendors;
    }
    // save, add, delete, update methods similarly
}