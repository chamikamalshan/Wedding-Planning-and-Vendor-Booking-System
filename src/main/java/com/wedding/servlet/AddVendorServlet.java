package com.wedding.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class AddVendorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        // read form, save via DAO, redirect
        res.sendRedirect("listVendors");
    }
}