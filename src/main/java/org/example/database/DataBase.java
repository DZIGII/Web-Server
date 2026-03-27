package org.example.database;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private static List<Quote> quotes = new ArrayList<>();

    public static void loadQuotes() {
        try {

            String json = Files.readString(Paths.get("quotes.json"));

            if (json == null)
                throw new RuntimeException();

            Gson gson = new Gson();
            Quote[] loadedQuotes = gson.fromJson(json, Quote[].class);

            quotes.clear();

            if (loadedQuotes != null) {
                for (Quote q : loadedQuotes) {
                    quotes.add(q);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addQuote(Quote quote) {
        try {
            quotes.add(quote);

            Gson gson = new Gson();
            String json = gson.toJson(quotes);

            Files.write(
                    Paths.get("quotes.json"),
                    json.getBytes((StandardCharsets.UTF_8))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Quote returnRandomQuote() {
        return  quotes.get((int) (Math.random() * quotes.size()));
    }

    public static List<Quote> getQuotes() {
        return quotes;
    }
}
