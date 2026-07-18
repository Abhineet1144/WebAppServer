package com.webserver.stream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HTTPInputStream extends BufferedInputStream {


    public HTTPInputStream(InputStream in) {
        super(in);
    }

    public String readLine() throws IOException {
        StringBuilder line = new StringBuilder();
        int i;
        while ((i = read()) != 10) {
            if (i == -1) {
                return null;
            }
            line.append((char) i);
        }
        return line.toString();
    }

    public byte[] readExactly(int size) throws IOException {

        byte[] b = new byte[size];

        for (int i = 0; i < size; i++) {
            b[i] = (byte) read();
        }
        return b;
    }
}
