package org.example.service1;

import com.google.gson.Gson;
import org.example.database.Quote;

public class Request {

    private final HttpMethod httpMethod;
    private final String path;
    private final String body;

    public Request(HttpMethod httpMethod, String path, String body) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.body = body;
    }

    public String toJson(String body) {
        String author = "";
        String quoteText = "";

        for (String pair : body.split("&")) {
            String[] parts = pair.split("=", 2);

            String key = parts[0];
            String value = parts[1];

            if (key.equals("author")) {
                author = value;
            } else if (key.equals("quote")) {
                quoteText = value;
            }
        }

        Quote quote = new Quote(author.replace('+', ' '), quoteText.replace('+', ' '));

        Gson gson = new Gson();
        String json = gson.toJson(quote);

        return json;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public String getBody() {
        return body;
    }
}
