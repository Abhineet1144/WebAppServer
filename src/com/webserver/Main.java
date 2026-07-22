package com.webserver;

import java.io.IOException;
import com.test.HelloServlet;
import com.test.TestMapper;

public class Main {
    public static void main(String[] args) throws IOException {

        HTTPServer server = new HTTPServer(8080);

        server.setRequestRouter(new TestMapper());
        server.start();

        server.stop();
    }
}
