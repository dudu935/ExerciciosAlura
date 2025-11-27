package org.example.ClienteHttpUtil;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class CLienteHTTP {
    public final HttpClient clienteHTTP;
    Gson json = new GsonBuilder().setPrettyPrinting().create();

    public CLienteHTTP() {
        this.clienteHTTP = HttpClient.newHttpClient();
    }

    public HttpResponse criadorDeResposta() throws IOException, InterruptedException {
        System.out.println("Digite o nome da receita que quer.");
        Scanner sc = new Scanner(System.in);
        String receitaUrl = sc.nextLine();
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + URLEncoder.encode(receitaUrl, "UTF-8");
        System.out.println("Enviando requisição para a URL: " + url);
        Request request = new Request(url);
        HttpResponse<String> response = clienteHTTP.send(request.getRequest(), HttpResponse.BodyHandlers.ofString());

        return response;
    }
}
