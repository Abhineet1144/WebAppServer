package com.webserver.parser;

import com.webserver.HTTPRequest;
import com.webserver.RequestType;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.webserver.component.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTTPRequestParser {

    private static final Logger logger = LoggerFactory.getLogger(HTTPRequestParser.class);

    public static HTTPRequest parse(InputStream inputStream) throws IOException {

        logger.debug("Starting HTTP request parsing");

        InputStream bufferedReader = inputStream;
        String requestLine = "";

        RequestType requestType = null;
        String target = "";
        String httpVersion = "";
        Map<String, String> queryParameters = new HashMap<>();
        Headers headers = new Headers();
        Map<String, String> cookies = new HashMap<>();

        // Check first line
        boolean firstLine = true;

        while ((requestLine = readHttpLine(bufferedReader)) != null && !requestLine.isEmpty()) {
            String[] parts;
            if (firstLine) {
                parts = requestLine.split(" ");

                requestType = RequestType.valueOf(parts[0].toUpperCase());
                target = parts[1];
                httpVersion = parts[2].replace("HTTP/", "");

                firstLine = false;

                String queryString = !target.contains("?") ? null : target.substring(target.indexOf("?") + 1);
                if (queryString != null) {
                    String[] queries = queryString.split("&");
                    for (String query : queries) {
                        String encodedQuery = URLDecoder.decode(query, StandardCharsets.UTF_8);
                        queryParameters.put(encodedQuery.split("=")[0], encodedQuery.split("=")[1]);
                    }
                }
            } else {
                parts = requestLine.split(":");
                if (parts[0].equals("Cookie")) {
                    for (String cookie : parts[1].split(";")) {
                        cookies.put(cookie.split("=")[0], cookie.split("=")[1]);
                    }
                } else {
                    headers.setHeader(parts[0], parts[1].substring(1));
                }
            }
        }

        return new HTTPRequest(requestType, target, httpVersion, queryParameters, headers, cookies, inputStream);
    }

    public static String readHttpLine(InputStream input) throws IOException {
        StringBuilder line = new StringBuilder();

        while (true) {
            int value = input.read();
            if (value == -1) {
                if (line.isEmpty()) {
                    return null;
                }
            }
            if (value == '\n') {
                int length = line.length();

                if (length > 0 && line.charAt(length - 1) == '\r') {
                    line.deleteCharAt(length - 1);
                }

                return line.toString();
            }
            line.append((char) value);
        }
    }
}
