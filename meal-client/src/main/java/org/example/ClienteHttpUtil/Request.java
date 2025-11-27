package org.example.ClienteHttpUtil;

import java.net.URI;
import java.net.http.HttpRequest;

public class Request {
    public HttpRequest request;

    public Request(String uri) {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
    }

    public HttpRequest getRequest() {
        return request;
    }



}
