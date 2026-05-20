package com.wedding.servlet;

import com.wedding.dao.VendorDAO;
import com.wedding.model.Vendor;
import com.wedding.util.AppContextListener;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Handles READ - list and search all vendors.
 */
@WebServlet("/listVendors")
public class ListVendorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        VendorDAO dao = (VendorDAO) getServletContext().getAttribute(AppContextListener.VENDOR_DAO_ATTR);
        String keyword = req.getParameter("keyword");

        List<Vendor> vendors = (keyword == null || keyword.trim().isEmpty())
                ? dao.getAllVendors()
                : dao.searchVendors(keyword);

        req.setAttribute("vendors", vendors);
        req.setAttribute("keyword", keyword == null ? "" : keyword);
        req.getRequestDispatcher("/listVendors.jsp").forward(req, resp);
    }
}
