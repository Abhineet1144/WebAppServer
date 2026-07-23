package com.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

import com.webserver.HTTPRequest;
import com.webserver.HTTPResponse;
import com.webserver.component.Headers;
import com.webserver.parser.HTTPRequestParser;

public class Testings {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(1234);
        var client = socket.accept();

        client.close();
        socket.close();
    }
}
