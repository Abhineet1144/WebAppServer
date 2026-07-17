package com.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

import com.webserver.HTTPRequest;
import com.webserver.HTTPResponse;
import com.webserver.parser.HTTPRequestParser;
import com.webserver.stream.HTTPInputStream;

public class Testings {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(1234);
        var client = socket.accept();
        var ci = new BufferedInputStream(client.getInputStream());
        HTTPRequest httpRequest = HTTPRequestParser.parse(ci);
        HTTPInputStream httpInputStream = new HTTPInputStream(httpRequest.getBody(), Integer.parseInt(httpRequest.getHeaders().get("Content-Length")));
        System.out.println(httpRequest);
        int i;
        for (int j = 0; j < Integer.parseInt(httpRequest.getHeaders().get("Content-Length")); j++) {
            System.out.print((char) ci.read());
        }

        HTTPResponse resp = new HTTPResponse(httpRequest, 200, "OK", new HashMap<>(), client.getOutputStream());
//        resp.getSingleUseStream().sendFile(new File("src/com/webserver/parser/HTTPRequestParser.java"));
        resp.getSingleUseStream().print("Done");
        client.close();
        socket.close();
    }
}
