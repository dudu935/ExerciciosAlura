package ClienteHttpUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import com.google.gson.*;


import java.util.Scanner;


public class ClienteHTTP {

    private final HttpClient clienteHTTP;

    Gson json = new GsonBuilder().setPrettyPrinting().create();

    public ClienteHTTP() {
        this.clienteHTTP = HttpClient.newHttpClient();
    }

    public String criadorUrl() throws UnsupportedEncodingException {
        System.out.println("Digite o nome do livro.");
        Scanner sc = new Scanner(System.in);
        String livroUrl = sc.nextLine();

        String urlCodificada = URLEncoder.encode(livroUrl, "UTF-8");

        String urlApi = "https://www.googleapis.com/books/v1/volumes?q=";
        return urlApi + urlCodificada;
    }

    public HttpResponse criadorDeResposta(String url) throws IOException, InterruptedException {
        Requisição request = new Requisição(url);
        HttpResponse<String> response = clienteHTTP.send(request.getRequest(), HttpResponse.BodyHandlers.ofString());

        return response;
    }
}



        





