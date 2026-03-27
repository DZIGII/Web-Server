package org.example.service1;

import org.example.database.DataBase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int PORT = 8080;

    static void main() throws IOException {
        DataBase.loadQuotes();
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(PORT);

            while (true) {
                Socket socket = ss.accept();
                new Thread(new ServerThread(socket))
                        .start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
    }

}
