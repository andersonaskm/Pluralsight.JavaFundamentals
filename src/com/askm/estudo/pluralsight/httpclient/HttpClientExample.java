package com.askm.estudo.pluralsight.httpclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class HttpClientExample {

    private static String accessToken = "9d9a372a9b064f3971f262b23627256e7e86d9630964d995d48ade12cc007748";
    private static String apiUrl = "http://api.olhovivo.sptrans.com.br/v2.1";
    private static String apiLogin = HttpClientExample.apiUrl + "/Login/Autenticar?token=" + HttpClientExample.accessToken;
    private static String apiCorredores = HttpClientExample.apiUrl + "/Corredor";
    private static CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
    private static HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .cookieHandler(cookieManager)
            .build();

    private HttpClientExample() {

    }

    public static String testHttpClient() throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("https://pluralsight.com"))
                .build();
        HttpResponse<String> httpResponse = HttpClientExample.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return httpResponse.body();
    }

    public static String olhoVivoLogin() throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(HttpClientExample.apiLogin))
                .POST(HttpRequest.BodyPublishers.noBody())
                .header("HeaderName","HeaderValue")
                .build();
        HttpResponse<String> httpResponse = HttpClientExample.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return httpResponse.body();
    }

    public static CorredorOnibus[] olhoVivoCorredores() throws InterruptedException, ExecutionException, IOException {

        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(HttpClientExample.apiCorredores))
                .GET()
                .build();
        CompletableFuture<HttpResponse<String>> completableFuture = HttpClientExample.httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        String corredores = completableFuture.thenApply(HttpResponse::body).get();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        CorredorOnibus[] corredoresOnibus = objectMapper.readValue(corredores, CorredorOnibus[].class);

        return corredoresOnibus;



    }

    public static String getFreeExchangeRate() throws IOException, InterruptedException {

        String exchangeRateAPIUrl = "https://open.exchangerate-api.com/v6/latest";
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(exchangeRateAPIUrl))
                .GET()
                .build();

        HttpResponse<String> response = HttpClientExample.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static void getWebSocketMessages(int msgCount) {

        CountDownLatch receiveLatch = new CountDownLatch(msgCount);

        CompletableFuture<WebSocket> wsFuture = HttpClient.newHttpClient()
                .newWebSocketBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .buildAsync(URI.create("ws://echo.websocket.org"), new WebSocketListenerExample(receiveLatch));

        wsFuture.thenAccept(webSocket -> {
            webSocket.request(msgCount);
            for (int i=0; i < msgCount; i++)
                webSocket.sendText("Message "+ i, true);
        });
    }

}
