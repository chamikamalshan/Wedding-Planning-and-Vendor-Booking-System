package com.wedding.util;

import com.wedding.dao.VendorDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@WebListener
public class AppContextListener implements ServletContextListener {

    public static final String VENDOR_FILE_ATTR = "vendorFilePath";
    public static final String VENDOR_DAO_ATTR  = "vendorDAO";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        // Writable location: <user.home>/wedding-planner-data/vendors.txt
        String home = System.getProperty("user.home");
        File dataDir = new File(home, "wedding-planner-data");
        if (!dataDir.exists()) dataDir.mkdirs();
        File dataFile = new File(dataDir, "vendors.txt");

        // Seed from bundled sample on first run only
        if (!dataFile.exists() || dataFile.length() == 0) {
            try (InputStream in = ctx.getResourceAsStream("/WEB-INF/data/vendors.txt")) {
                if (in != null) {
                    Files.copy(in, dataFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } else {

                    dataFile.createNewFile();
                }
            } catch (IOException e) {
                ctx.log("Could not seed vendors.txt", e);
            }
        }

        ctx.setAttribute(VENDOR_FILE_ATTR, dataFile.getAbsolutePath());
        ctx.setAttribute(VENDOR_DAO_ATTR,  new VendorDAO(dataFile.getAbsolutePath()));
        ctx.log("Wedding Planner data file: " + dataFile.getAbsolutePath());
    }
}
