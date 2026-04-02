package org.example.service1;

import com.google.gson.Gson;
import org.example.database.Quote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class QuoteOfTheDay {

    public static String getQuoteJson() {
        try (
                Socket socket = new Socket("localhost", 8081);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.print("GET /quote-of-the-day HTTP/1.1\r\n");
            out.print("Host: localhost\r\n");
            out.print("\r\n");
            out.flush();

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String[] parse(String response) {

        String json = response.substring(response.indexOf("{")).trim();

        Gson gson = new Gson();
        Quote quote = gson.fromJson(json, Quote.class);

        return new String[]{quote.getAuthor(), quote.getQuote()};
    }
}