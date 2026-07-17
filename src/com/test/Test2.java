package com.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class Test2 {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(1234);
        var client = socket.accept();
        var in = new BufferedInputStream(client.getInputStream());
        int chr;
        while ((chr = in.read()) != -1) {
            System.out.print((char) chr);
        }
        client.getOutputStream().write("hello".getBytes());
        client.getOutputStream().flush();
        client.close();
        socket.close();
    }
}