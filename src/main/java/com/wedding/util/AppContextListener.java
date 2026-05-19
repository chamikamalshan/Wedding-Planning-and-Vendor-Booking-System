package com.wedding.util;

import jakarta.servlet.*;
import java.io.*;

public class AppContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        String path = sce.getServletContext().getRealPath("/WEB-INF/data/vendors.txt");
        File file = new File(path);
        if (!file.exists()) {
            // create sample data
        }
        System.out.println("App started: " + path);
    }
    public void contextDestroyed(ServletContextEvent sce) {}
}