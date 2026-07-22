package com.webserver.handler;

import java.io.IOException;

import com.webserver.HTTPRequest;
import com.webserver.HTTPResponse;

public class BaseHTTPHandler {
    public void service(HTTPRequest request, HTTPResponse response) throws IOException {

        switch (request.getRequestType()) {
        case POST -> doPost(request, response);
        case PUT -> doPut(request, response);
        case DELETE -> doDelete(request, response);
        default -> doGet(request, response);
        }
    }

    protected void doGet(HTTPRequest request, HTTPResponse response) throws IOException {
        response.getSingleUseStream().print("404 Not Found");
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
