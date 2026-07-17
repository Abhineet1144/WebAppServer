package com.webserver.stream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import com.webserver.HTTPResponse;
import com.webserver.parser.HTTPResponseFormatter;

public class HTTPOutputStream extends BufferedOutputStream {
    private final boolean isSingleUse;
    private HTTPResponse resp;

    private boolean used = false;

    private HTTPOutputStream(OutputStream out, HTTPResponse resp, boolean isSingleUse) {
        super(out);
        this.resp = resp;
        this.isSingleUse = isSingleUse;
    }

    public static HTTPOutputStream getSingleUseOutputStream(HTTPResponse resp) {
        return new HTTPOutputStream(resp.getOutputStream(), resp,  true);
    }

    public void sendFile(File file) throws IOException {
        if (used && isSingleUse) {
            throw new IllegalStateException("Output stream used more than once in single use mode");
        }
        if (isSingleUse) {
            resp.setHeader("Content-Length", file.length() + "");
            write(HTTPResponseFormatter.formatResponse(resp).getBytes());
            write(10);
            IOUtils.copy(new FileInputStream(file), this);
            used = true;
            flush();
        }
    }

    public void print(String content) throws IOException {
        if (used && isSingleUse) {
            throw new IllegalStateException("Output stream used more than once in single use mode");
        }
        if (isSingleUse) {
            resp.setHeader("Content-Length", content.length() + "");
            write(HTTPResponseFormatter.formatResponse(resp).getBytes());
            write(10);
            write(content.getBytes());
            used = true;
            flush();
        }
    }


}
