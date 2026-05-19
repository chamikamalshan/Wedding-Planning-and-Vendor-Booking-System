package com.wedding.servlet;

import com.wedding.dao.VendorDAO;
import com.wedding.util.AppContextListener;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Handles DELETE - remove a vendor by id.
 *
 * GET /deleteVendor?id=...
 */
@WebServlet("/deleteVendor")
public class DeleteVendorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        VendorDAO dao = (VendorDAO) getServletContext().getAttribute(AppContextListener.VENDOR_DAO_ATTR);

        boolean removed = dao.deleteVendor(id);
        String msg = removed ? "deleted" : "notfound";
        resp.sendRedirect(req.getContextPath() + "/listVendors?msg=" + msg);
    }
}
