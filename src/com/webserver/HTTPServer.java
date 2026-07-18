package com.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webserver.parser.HTTPRequestParser;

public class HTTPServer {

    private static int port = 8080;
    private static ExecutorService threadPool;
    private static ServerSocket socket;
    private static volatile boolean running;

    private static final Logger logger = LoggerFactory.getLogger(HTTPRequestParser.class);

    protected static void start() {
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            logger.error("Could not start server on port {}", 8080, e);
        }
        threadPool = Executors.newFixedThreadPool(10);
        running = true;

        logger.info("Server listening on port {}", port);
        ;

        while (running) {
            try {
                Socket client = socket.accept();
                logger.debug("Accepted connection from {}:{}", client.getInetAddress().getHostAddress(),
                        client.getPort());
                threadPool.submit(new HTTPRequestHandler(client));
            } catch (IOException e) {
                logger.error("Failed to accept client connection", e);
            }
        }
    }
}
