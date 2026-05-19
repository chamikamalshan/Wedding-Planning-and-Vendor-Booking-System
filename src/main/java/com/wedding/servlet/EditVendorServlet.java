package com.wedding.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class EdiVendorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        // read form, save via DAO, redirect
        res.sendRedirect("listVendors");
    }
}