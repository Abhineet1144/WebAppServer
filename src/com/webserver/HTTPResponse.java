package com.webserver;

import java.io.OutputStream;
import java.util.Map;

import com.webserver.stream.HTTPOutputStream;

public class HTTPResponse {
    private final HTTPRequest request;
    private final int statusCode;
    private final String statusPhrase;
    private final Map<String, String> headers;
    private final OutputStream out;

    public HTTPResponse(HTTPRequest request, int statusCode, String statusPhrase, Map<String, String> headers,
            OutputStream out) {
        this.request = request;
        this.statusCode = statusCode;
        this.statusPhrase = statusPhrase;
        this.headers = headers;
        this.out = out;
    }

    public HTTPRequest getRequest() {
        return request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusPhrase() {
        return statusPhrase;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeader(String key, String value) {
        headers.put(key, value);
    }

    public HTTPOutputStream getSingleUseStream() {
        HTTPOutputStream out = HTTPOutputStream.getSingleUseOutputStream(this);
        return out;
    }

    public OutputStream getOutputStream() {
        return out;
    }
}
