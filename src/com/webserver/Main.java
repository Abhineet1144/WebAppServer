package com.webserver;

import java.io.IOException;
import com.test.HelloServlet;

public class Main {
    public static void main(String[] args) throws IOException {

        HTTPServer server = new HTTPServer(8080);

        server.addServlet("/hello", new HelloServlet());

        server.start();

        server.stop();
    }
}
