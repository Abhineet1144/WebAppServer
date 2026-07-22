package com.webserver;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.webserver.handler.BaseHTTPHandler;
import com.webserver.route.RequestRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webserver.parser.HTTPRequestParser;

public class HTTPRequestHandler implements Runnable {
    private final Socket client;
    private final RequestRouter requestRouter;

    private static final Logger logger = LoggerFactory.getLogger(HTTPRequestHandler.class);

    HTTPRequestHandler(Socket client, RequestRouter servletMapper) {
        this.client = client;
        this.requestRouter = servletMapper;
    }

    @Override
    public void run() {
        try {
            Map<String, String> respHeaders = new HashMap<>();
            HTTPRequest request = HTTPRequestParser.parse(new BufferedInputStream(client.getInputStream()));
            logger.info("{} {} from {}", request.getRequestType(), request.getTarget(),
                    client.getInetAddress().getHostAddress());

            HTTPResponse response = new HTTPResponse(request, 200, "OK", respHeaders, client.getOutputStream());

            requestRouter.getRouteOrder(request.getTarget()).startNextHandler(request, response);

        } catch (IOException e) {
            logger.error("Unexpected error while handling {}", client.getRemoteSocketAddress(), e);
        }
    }
}