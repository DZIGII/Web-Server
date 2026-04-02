package org.example.service2;

import java.util.List;
import java.util.Random;

public class RequestHandler {

    private static final List<String[]> quotes = List.of(
            new String[]{"Steve Jobs", "Stay hungry, stay foolish."},
            new String[]{"Oscar Wilde", "Be yourself; everyone else is already taken."},
            new String[]{"Nietzsche", "He who has a why can bear almost any how."}
    );

    public String handleRequest(String method, String path) {

        if (method.equals("GET") && path.equals("/quote-of-the-day")) {

            Random broj = new Random();
            String[] q = quotes.get(broj.nextInt(quotes.size()));

            String json = "{ \"author\": \"" + q[0] + "\", \"quote\": \"" + q[1] + "\" }";

            return "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: application/json\r\n\r\n" +
                    json;

        }

        return "HTTP/1.1 404 Not Found\r\n\r\n";
    }

}
