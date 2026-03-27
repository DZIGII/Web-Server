package org.example.service1;

import org.example.html.HtmlTemplate;
import org.example.service1.response.HtmlResponse;
import org.example.service1.response.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerThread implements Runnable {

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run()  {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


            String requestLine = in.readLine();

            StringTokenizer stringTokenizer = new StringTokenizer(requestLine);

            String method = stringTokenizer.nextToken();
            String path = stringTokenizer.nextToken();

            int contetnLen = 0;


            System.out.println("\nHTTP ZAHTEV KLIJENTA:\n");

            do {
                System.out.println(requestLine);
                requestLine = in.readLine();

                if (requestLine.contains("Content-Length:")) {
                    contetnLen = Integer.parseInt(requestLine.split(":")[1].trim());
                }
            } while (!requestLine.trim().equals(""));

            String body = null;

            if (method.equals(HttpMethod.POST.toString())) {
                char[] buffer = new char[contetnLen];
                in.read(buffer);

                body = new String(buffer);
            }

            Request request = new Request(HttpMethod.valueOf(method), path, body);
            RequestHandler requestHandler = new RequestHandler();

            Response response = requestHandler.handleRequest(request);

            out.println(response.getResponseString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
