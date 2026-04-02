package org.example.service2;

import java.io.BufferedReader;
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
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Socket s = socket
        ) {
            String requestLine = in.readLine();

            if (requestLine == null || requestLine.isBlank()) {
                return;
            }

            StringTokenizer st = new StringTokenizer(requestLine);
            String method = st.nextToken();
            String path = st.nextToken();

            String line;
            while ((line = in.readLine()) != null && !line.equals("")) {
            }

            RequestHandler handler = new RequestHandler();
            String response = handler.handleRequest(method, path);

            out.print(response);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}