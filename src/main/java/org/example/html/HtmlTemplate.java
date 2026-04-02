package org.example.html;

import org.example.database.DataBase;
import org.example.database.Quote;

public class HtmlTemplate {

    public static String getHtml(String author, String q) {

        String quotes = "";

        for (Quote quote : DataBase.getQuotes()) {
            quotes += """
                <div class="quote"> 
                    <p>""" + quote.getAuthor() + ":<i>" + quote.getQuote() + "</i>" +
                            """
                    </p>
                </div>
                """;
        }

        String html = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>quotes</title>
            <style>
        
                * {
                    box-sizing: border-box;
                    padding: 0;
                    margin: 0;
                }
        
                body {
                    font-family: Arial, Helvetica, sans-serif;
                }
        
                .wrapper {
                    max-width: 500px;
                    margin: 0 auto;
                }
        
                .input-container {
                    width: 100%;
                    display: flex;
                    flex-direction: column;
                    align-items: flex-start;
                    align-content: center;
                    gap: 5px;
                }
        
                .mt {
                    margin-top: 30px;
                }
        
                .mt-small {
                    margin-top: 10px;
                }
        
                p, label, input, button {
                    font-size: 20px;
                }
        
                h2 {
                    font-size: 30px;
                }
        
                input {
                    padding: 5px 10px;
                    border-radius: 7px;
                    border: 1px solid rgb(80, 80, 80);
                    width: 100%;
                }
        
                button {
                    padding: 7px 14px;
                    border-radius: 7px;
                    background-color: #006cf9;
                    border: none;
                    color: #fff;
                    cursor: pointer;
                }
        
                .button-container {
                    width: 100%;
                    display: flex;
                    justify-content: end;
                }
        
                .quotes-container {
                    display: flex;
                    flex-direction: column;
                }
        
                .quote {
                    border: 1px solid gray;
                    padding: 20px;
                }
        
            </style>
        </head>
        <body>
            <div class="wrapper">
        
                <form action="/save-quote" method="post" class="mt">
                    <div class="input-container">
                        <label>Autor</label>
                        <input type="text" name="author" placeholder="Autor">
                    </div>
        
                    <div class="input-container mt">
                        <label>Citat</label>
                        <input type="text" name="quote" placeholder="Citat">
                    </div>
        
                    <div class="button-container mt">
                        <button type="submit">Sacuvaj Citat</button>
                    </div>
                </form>
        
                <div class="quote-of-day mt">
                    <h2>Citat dana:</h2>
                    <br>
                    <p>""" + author + ": <i>" + q + "</i></p>" + """
                </div>
        
                <div class="quotes mt">
                    <h2>Svi Citati</h2>
        
                    <div class="quotes-container mt-small">
                        """ + quotes + """
                    </div>
                </div>
        
            </div>
        </body>
        </html>
        """;

        return  html;
    }

}
