package com.sye010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHTTPServer1 
{
    public static void main( String[] args ) throws IOException
    {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Server is listening on port 8080");
        while (true){
            Socket client = server.accept();
            System.out.println("New client connected");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream())) {
                
                int oneInt = -1;
                while(-1 != (oneInt = in.read())){
                    System.out.print((char)oneInt);
                }

                String response = "HTTP/1.1 200 OknnHello, World!";
                out.print(response);
                out.flush();
            }
            client.close();
        }

    }
}
