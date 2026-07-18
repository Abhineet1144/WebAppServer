package com.webserver.servlet;

import java.io.IOException;

import com.webserver.HTTPRequest;
import com.webserver.HTTPResponse;

public abstract class BaseHTTPServlet implements HTTPServlet {
    @Override
    public void service(HTTPRequest request, HTTPResponse response) throws IOException {

        switch (request.getRequestType()) {
        case GET -> doGet(request, response);
        case POST -> doPost(request, response);
        case PUT -> doPut(request, response);
        case DELETE -> doDelete(request, response);
        }
    }

    protected void doGet(HTTPRequest request, HTTPResponse response) throws IOException {
        // TODO: add error statement
    }

    protected void doPost(HTTPRequest request, HTTPResponse response) throws IOException {
        // TODO: add error statement
    }

    protected void doPut(HTTPRequest request, HTTPResponse response) throws IOException {
        // TODO: add error statement
    }

    protected void doDelete(HTTPRequest request, HTTPResponse response) throws IOException {
        // TODO: add error statement
    }
}
