package com.webserver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    protected static void start() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(8080);
        } catch (IOException e) {
            return;
        }
        while (true) {
            try {
                Socket client = socket.accept();
            } catch (IOException e) {
            }
        }
    }
}
