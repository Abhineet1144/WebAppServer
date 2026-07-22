package com.test;

import java.io.IOException;

import com.webserver.HTTPRequest;
import com.webserver.HTTPResponse;
import com.webserver.handler.BaseHTTPHandler;
import com.webserver.stream.HTTPInputStream;

public class HelloServlet extends BaseHTTPHandler {

    String hello = "hello";

    @Override
    protected void doPost(HTTPRequest request, HTTPResponse response) throws IOException {
        HTTPInputStream httpInputStream = new HTTPInputStream(request.getBody());
        String str = new String(httpInputStream.readExactly(Integer.parseInt(request.getHeaders().get("Content-Length"))));
        System.out.println(str);
        hello = str;
        response.getSingleUseStream().print(str);
    }

    @Override
    protected void doGet(HTTPRequest request, HTTPResponse response) throws IOException {
        response.getSingleUseStream().print(hello);
    }
}
