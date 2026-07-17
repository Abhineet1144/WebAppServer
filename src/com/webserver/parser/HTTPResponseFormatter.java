package com.webserver.parser;

import java.util.Map.Entry;

import com.webserver.HTTPResponse;

public class HTTPResponseFormatter {
    private static final String HTTP_PREFIX = "HTTP/";

    public static String formatResponse(HTTPResponse response) {
        StringBuilder responseStr = new StringBuilder();
        responseStr.append(HTTP_PREFIX).append(response.getRequest().getHttpVersion()).append(" ").append(response.getStatusCode())
                .append(" ").append(response.getStatusPhrase()).append("\n");
        for (Entry<String, String> headerEntry : response.getHeaders().entrySet()) {
            responseStr.append(headerEntry.getKey()).append(": ").append(headerEntry.getValue()).append("\n");
        }
        return responseStr.toString();
    }
}
