package com.webserver;

import java.io.InputStream;
import java.util.Map;

public class HTTPRequest {

    private final RequestType requestType;
    private final String target;
    private final String httpVersion;
    private final String host;
    private final Map<String, String> queryParam;
    private final Map<String, String> headers;
    private final Map<String, String> cookies;
    private final InputStream body;

    public HTTPRequest(RequestType requestType, String target, String httpVersion, String host, Map<String, String> queryParam,
            Map<String, String> headers, Map<String, String> cookies, InputStream body) {
        this.requestType = requestType;
        this.target = target;
        this.httpVersion = httpVersion;
        this.host = host;
        this.queryParam = queryParam;
        this.headers = headers;
        this.cookies = cookies;
        this.body = body;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getTarget() {
        return target;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public String getHost() {
        return host;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public Map<String, String> getQueryParam() {
        return queryParam;
    }

    public InputStream getBody() {
        return body;
    }

}
