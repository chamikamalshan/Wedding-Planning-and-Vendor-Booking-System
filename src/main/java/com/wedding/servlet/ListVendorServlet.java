package com.wedding.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.wedding.dao.VendorDAO;
import java.util.*;

public class ListVendorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        VendorDAO dao = new VendorDAO();
        req.setAttribute("vendors", dao.getAllVendors());
        req.getRequestDispatcher("listVendors.jsp").forward(req, res);
    }
}