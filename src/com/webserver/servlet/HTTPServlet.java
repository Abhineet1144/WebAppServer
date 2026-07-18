package com.webserver.servlet;

import java.io.IOException;

import com.webserver.HTTPRequest;
import com.webserver.HTTPResponse;

public interface HTTPServlet {
    public void service(HTTPRequest request, HTTPResponse response) throws IOException;
}
