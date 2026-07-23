package com.webserver;

import com.webserver.component.Headers;
import com.webserver.stream.HTTPChunkedInputStream;

import java.io.InputStream;
import java.util.Map;

public class HTTPRequest {

    private final RequestType requestType;
    private final String target;
    private final String httpVersion;
    private final String host = null;
    private final Map<String, String> queryParams;
    private final Headers headers;
    private final Map<String, String> cookies;
    private final InputStream body;

    public HTTPRequest(RequestType requestType, String target, String httpVersion, Map<String, String> queryParams,
            Headers headers, Map<String, String> cookies, InputStream body) {
        this.requestType = requestType;
        this.target = target;
        this.httpVersion = httpVersion;
        this.queryParams = queryParams;
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

    public Headers getHeaders() {
        return headers;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public InputStream getBody() {
        if (headers.getHeader("Transfer-Encoding").equals("chunked")) {
            return new HTTPChunkedInputStream(body);
        }
        return body;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("RequestType: ").append(requestType).append("\ntarget: ").append(target).append("\nhttpVersion: ")
                .append(httpVersion).append("\nhost: ").append(host).append("\nqueryParams: ").append(queryParams)
                .append("\nheaders: ").append(headers).append("\nCookies: ").append(cookies);
        return str.toString();
    }
}
