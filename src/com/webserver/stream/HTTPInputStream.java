package com.webserver.stream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HTTPInputStream extends BufferedInputStream {

    public HTTPInputStream(InputStream in) {
        super(in);
    }

    public HTTPInputStream(InputStream in, int size) {
        super(in, size);
    }
}
