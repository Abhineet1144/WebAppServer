package com.webserver;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webserver.parser.HTTPRequestParser;
import com.webserver.servlet.HTTPServlet;
import com.webserver.servlet.ServletMapper;

public class HTTPRequestHandler implements Runnable {
    private final Socket client;
    private final ServletMapper servletMapper;

    private static final Logger logger = LoggerFactory.getLogger(HTTPRequestHandler.class);

    HTTPRequestHandler(Socket client, ServletMapper servletMapper) {
        this.client = client;
        this.servletMapper = servletMapper;
    }

    @Override
    public void run() {
        try {
            Map<String, String> respHeaders = new HashMap<>();
            HTTPRequest request = HTTPRequestParser.parse(new BufferedInputStream(client.getInputStream()));
            logger.info("{} {} from {}", request.getRequestType(), request.getTarget(),
                    client.getInetAddress().getHostAddress());

            HTTPResponse response = new HTTPResponse(request, 200, "OK", respHeaders, client.getOutputStream());

            HTTPServlet servlet = servletMapper.findServlet(request.getTarget());
            if (servlet != null) servlet.service(request, response);

        } catch (IOException e) {
            logger.error("Unexpected error while handling {}", client.getRemoteSocketAddress(), e);
        }
    }
}