package com.webserver;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.webserver.parser.HTTPRequestParser;

public class HTTPRequestHandler implements Runnable {
    private final Socket client;

    HTTPRequestHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            Map<String, String> respHeaders = new HashMap<>();
            HTTPRequest request = HTTPRequestParser.parse(new BufferedInputStream(client.getInputStream()));
            HTTPResponse response = new HTTPResponse(request, 200, "OK", respHeaders, client.getOutputStream());
        } catch (IOException e) {
        }
    }
}
