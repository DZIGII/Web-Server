package org.example.service1;

import org.example.database.DataBase;
import org.example.database.Quote;
import org.example.html.HtmlTemplate;
import org.example.service1.response.HtmlResponse;
import org.example.service1.response.RedirectResponse;
import org.example.service1.response.Response;

import java.util.ArrayList;

public class RequestHandler {

    public Response handleRequest(Request request) {

        if (request.getHttpMethod().equals(HttpMethod.GET) && request.getPath().equals("/quotes")) {

            String responseService2 = QuoteOfTheDay.getQuoteJson();
            String[] parsed = QuoteOfTheDay.parse(responseService2);

            String author = parsed[0];
            String quote = parsed[1];

            return new HtmlResponse(HtmlTemplate.getHtml(author, quote));
        }
        else if (request.getHttpMethod().equals(HttpMethod.GET) && request.getPath().equals("/")) {
            return new RedirectResponse("/quotes");
        }
        else if (request.getHttpMethod().equals(HttpMethod.POST) && request.getPath().equals("/save-quote")) {

            ArrayList<String> quote = new ArrayList<>();

            for (String str : request.getBody().split("&")) {
                String content = str.split("=", 2)[1].replace("+", " ");
                quote.add(content);
            }

            DataBase.addQuote(new Quote(quote.get(0), quote.get(1)));

            return new RedirectResponse("/quotes");
        }

        return new HtmlResponse("<h1>404 Not Found</h1>");
    }

}
