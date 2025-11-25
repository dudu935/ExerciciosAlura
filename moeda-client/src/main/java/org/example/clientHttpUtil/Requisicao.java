package org.example.clientHttpUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class Requisicao {
    private HttpRequest request;
    public Requisicao(String url) {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

    public HttpRequest getRequest() {
        return request;
    }

}
