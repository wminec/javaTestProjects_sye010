package com.sye010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SimpleHTTPServer2 
{
    private static final int PORT = 8080;
    public static void main( String[] args ) throws IOException
    {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server start with "+ PORT + " Port");
        while (true) {
            Socket client = server.accept();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(client.getOutputStream())) {

                        Map<String, String> headers = new HashMap<>();
 
                        String line;
                        while (!(line = in.readLine()).equals("")) {
                            System.out.println(line);
                            setHeaders(headers, line);
                        }
                        // if POST
                        String messageBody = "";
                        if ("application/x-www-form-urlencoded".equals(headers.get("Content-Type"))) {
                            messageBody = getMessageBody(in, headers);
                            System.out.println("Request Message Body ====================>");
                            System.out.println(messageBody);
                            System.out.println("====================");
                        }
         
                        out.println("HTTP/1.1 200 OK");
                        out.println("Content-Type: text/html;charset=UTF-8");
                        // this blank line signals the end of the headers
                        out.println();
                        // Send the HTML page
                        out.println("<H1>Hello, World!</H2>");
                        if (messageBody != "") {
                            out.println("<H3>Post data : " + messageBody + "</H3>");
                        }
                        out.println("<form name=\"input\" action=\"form_submited\" method=\"post\">");
                        out.println("Username: <input type=\"text\" name=\"user\">");
                        out.println("password : <input type=\"password\" name=\"pass\">");
                        out.println("<input type=\"submit\" value=\"Submit!\"></form>");
                        out.flush();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    client.close();
                }
            }
         
         
            private static void setHeaders(Map<String, String> headers, String line) {
                String[] parts = line.split(": ");
                // if line is header 
                // example : "Host: localhosot:8080"
                //System.out.println("setHeaders, print line : " + line);
                if (parts.length == 2) {
                    headers.put(parts[0], parts[1]);
                }
            }
         
            private static String getMessageBody(BufferedReader in, Map<String, String> headers) throws IOException {
                int contentLength = Integer.parseInt(headers.get("Content-Length"));
                char[] body = new char[contentLength];
                in.read(body, 0, contentLength);
                String messageBody = new String(body);
         
                return messageBody;
            }
}
