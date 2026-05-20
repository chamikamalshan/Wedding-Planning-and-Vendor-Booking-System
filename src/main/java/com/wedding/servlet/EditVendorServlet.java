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

/**
 * Handles UPDATE - edit existing vendor.
 */
@WebServlet("/editVendor")
public class EditVendorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        VendorDAO dao = (VendorDAO) getServletContext().getAttribute(AppContextListener.VENDOR_DAO_ATTR);
        Vendor vendor = dao.getVendorById(id);

        if (vendor == null) {
            resp.sendRedirect(req.getContextPath() + "/listVendors?msg=notfound");
            return;
        }

        req.setAttribute("vendor", vendor);
        req.getRequestDispatcher("/editVendor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id          = trim(req.getParameter("id"));
        String name        = trim(req.getParameter("name"));
        String email       = trim(req.getParameter("email"));
        String phone       = trim(req.getParameter("phone"));
        String serviceType = trim(req.getParameter("serviceType"));
        String company     = trim(req.getParameter("company"));
        String priceStr    = trim(req.getParameter("basePrice"));
        String availability= trim(req.getParameter("availability"));
        String category    = trim(req.getParameter("category"));

        VendorDAO dao = (VendorDAO) getServletContext().getAttribute(AppContextListener.VENDOR_DAO_ATTR);
        Vendor existing = dao.getVendorById(id);
        if (existing == null) {
            resp.sendRedirect(req.getContextPath() + "/listVendors?msg=notfound");
            return;
        }

        double basePrice;
        try {
            basePrice = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Base price must be a number.");
            req.setAttribute("vendor", existing);
            req.getRequestDispatcher("/editVendor.jsp").forward(req, resp);
            return;
        }

        // Build the updated vendor with the chosen subclass
        Vendor updated;
        if ("Premium".equalsIgnoreCase(category)) {
            updated = new PremiumVendor(id, name, email, phone,
                    serviceType, company, basePrice, availability);
        } else {
            updated = new StandardVendor(id, name, email, phone,
                    serviceType, company, basePrice, availability);
        }

        dao.updateVendor(updated);
        resp.sendRedirect(req.getContextPath() + "/listVendors?msg=updated");
    }

    private static String trim(String s) {
        return s == null ? "" : s.trim();
    }
}
