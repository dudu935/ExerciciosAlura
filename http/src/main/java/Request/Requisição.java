package Request;

import java.net.URI;
import java.net.http.HttpRequest;

public class Requisição {
    private HttpRequest request;



    public Requisição(String uri) {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
    }

    public HttpRequest getRequest() {
        return request;
    }
}
