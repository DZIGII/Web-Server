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

        Response response = null;

        if (request.getHttpMethod().equals(HttpMethod.GET)) {

            response = new HtmlResponse(HtmlTemplate.getHtml());

        }
        else if (request.getHttpMethod().equals(HttpMethod.POST)) {
            if (request.getPath().equals("/save-quote")) {

                ArrayList<String> quote = new ArrayList<>();

                for (String str : request.getBody().split("&")) {
                    String content = str.split("=")[1].replace("+", " ");
                    quote.add(content);
                }

                DataBase.addQuote(new Quote(quote.get(0), quote.get(1)));

            }
            else {

            }
        }
        else if (request.getHttpMethod().equals(HttpMethod.PUT)) {

        }
        else {

        }

        return response;
    }

}
