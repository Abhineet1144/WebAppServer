package com.webserver.parser;

import com.webserver.HTTPRequest;
import com.webserver.RequestType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HTTPRequestParser {

    public static HTTPRequest parse(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String requestLine;

        RequestType requestType = null;
        String target = "";
        String httpVersion = "";
        Map<String, String> queryParameters = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        Map<String, String> cookies = new HashMap<>();

        // Check first line
        boolean firstLine = true;

        while (!Objects.equals(requestLine = bufferedReader.readLine(), "")) {
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
                    headers.put(parts[0], parts[1].substring(1));
                }
            }
        }

        return new HTTPRequest(requestType, target, httpVersion, queryParameters, headers, cookies, inputStream);

        // TODO: Add Transfer-Encoding: chunked later
    }
}
