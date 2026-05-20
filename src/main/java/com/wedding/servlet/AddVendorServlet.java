package com.wedding.servlet;

import com.wedding.dao.VendorDAO;
import com.wedding.model.PremiumVendor;
import com.wedding.model.StandardVendor;
import com.wedding.model.Vendor;
import com.wedding.util.AppContextListener;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

/**
 * Handles CREATE - add a new vendor.
 */
@WebServlet("/addVendor")
public class AddVendorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/addVendor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name        = trim(req.getParameter("name"));
        String email       = trim(req.getParameter("email"));
        String phone       = trim(req.getParameter("phone"));
        String serviceType = trim(req.getParameter("serviceType"));
        String company     = trim(req.getParameter("company"));
        String priceStr    = trim(req.getParameter("basePrice"));
        String availability= trim(req.getParameter("availability"));
        String category    = trim(req.getParameter("category"));

        // Simple validation
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()
                || serviceType.isEmpty() || priceStr.isEmpty()) {
            req.setAttribute("error", "Please fill in all required fields.");
            req.getRequestDispatcher("/addVendor.jsp").forward(req, resp);
            return;
        }

        double basePrice;
        try {
            basePrice = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Base price must be a number.");
            req.getRequestDispatcher("/addVendor.jsp").forward(req, resp);
            return;
        }

        String id = "V-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Vendor reference points to Standard or Premium subclass
        Vendor vendor;
        if ("Premium".equalsIgnoreCase(category)) {
            vendor = new PremiumVendor(id, name, email, phone,
                    serviceType, company, basePrice, availability);
        } else {
            vendor = new StandardVendor(id, name, email, phone,
                    serviceType, company, basePrice, availability);
        }

        VendorDAO dao = (VendorDAO) getServletContext().getAttribute(AppContextListener.VENDOR_DAO_ATTR);
        dao.addVendor(vendor);

        resp.sendRedirect(req.getContextPath() + "/listVendors?msg=added");
    }

    private static String trim(String s) {
        return s == null ? "" : s.trim();
    }
}
