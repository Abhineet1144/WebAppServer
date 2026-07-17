package com.webserver.parser;

import com.webserver.HTTPRequest;

import java.io.InputStream;

public class HttpRequestParser {

    public HTTPRequest parse(InputStream inputStream) {

        // 1. Read request line
        // 2. Parse method, target, version
        // 3. Read headers until blank line
        // 4. Determine body length
        // 5. Read body bytes
        // 6. Parse path and query parameters
        // 7. Return HttpRequest
        return null;
    }
}
