package org.example.service2;

import org.example.service2.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static final int PORT = 8081;

    public static void main() throws IOException {

        ServerSocket ss = new ServerSocket(PORT);

        while (true) {
            Socket socket = ss.accept();
            new Thread(new ServerThread(socket))
                    .start();
        }

    }

}
