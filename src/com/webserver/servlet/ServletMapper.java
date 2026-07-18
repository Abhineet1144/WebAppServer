package com.webserver.servlet;

import java.util.HashMap;
import java.util.Map;

public class ServletMapper {
    private Map<String, HTTPServlet> servlets = new HashMap<>();

    public void addServlet(String path, HTTPServlet servlet) {
        servlets.put(path, servlet);
    }

    public HTTPServlet findServlet(String path) {
        return servlets.get(path);
    }
}
