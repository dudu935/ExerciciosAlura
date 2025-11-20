import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import com.google.gson.*;
import java.util.List;



public class ClienteHTTP {
    public static void main(String[] args) throws IOException, InterruptedException {
    HttpClient clienteHTTP = HttpClient.newHttpClient();
    Gson json = new GsonBuilder().setPrettyPrinting().create();

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://www.googleapis.com/books/v1/volumes?q=Harry+potter"))
        .GET()
        .build();

    HttpResponse<String> response = clienteHTTP.send(request, HttpResponse.BodyHandlers.ofString());
    BooksResponse books = json.fromJson(response.body(), BooksResponse.class);

    for (BooksResponse.Item item : books.items()) {
        volumeInfo volumeInfo = item.volumeInfo();
        System.out.println("titulo:" + volumeInfo.title());
        System.out.println("autor:" + volumeInfo.authors());
        System.out.println("descrição:" + volumeInfo.description());}


        
    }


}
