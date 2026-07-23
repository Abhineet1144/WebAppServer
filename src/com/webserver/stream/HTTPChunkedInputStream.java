package com.webserver.stream;

import com.webserver.parser.HTTPRequestParser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class HTTPChunkedInputStream extends BufferedInputStream {
    private long currRead = 0L;
    private boolean firstRead = true;
    private InputStream in;

    public HTTPChunkedInputStream(InputStream in) {
        super(in);
        this.in = in;
    }

    @Override
    public long skip(long n) throws IOException {
        currRead -= n;
        if (currRead < 0) {
            currRead = 0;
        }
        return super.skip(n);
    }

    @Override
    public int read() throws IOException {
        // TODO: Add trailer header
        if (currRead == 0L) {
            try {
                if (!firstRead) {
                    HTTPRequestParser.readHttpLine(in);
                }
                String line = Objects.requireNonNull(HTTPRequestParser.readHttpLine(in));
                String size = line.split(";", 2)[0];
                currRead = Long.parseLong(size, 16);
            } catch (NumberFormatException e) {
                currRead = -1;
                return -1;
            }
            if (currRead == 0) {
                currRead = -1;
                return -1;
            }
        } else if (currRead < 0) {
            return -1;
        }
        currRead--;
        return super.read();
    }
}
