package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class Testings {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(1234);
        var client = socket.accept();
        var in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        client.getOutputStream().write("hello".getBytes());
        client.getOutputStream().flush();
        client.close();
        socket.close();
    }
}
