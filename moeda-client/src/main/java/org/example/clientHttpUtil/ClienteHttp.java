package org.example.clientHttpUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ClienteHttp {
    private final HttpClient clienteHTTP;

    public ClienteHttp() {
        this.clienteHTTP = HttpClient.newHttpClient();
    }
    public HttpResponse<String> listarMoedas() throws IOException, InterruptedException{
        Requisicao requisiçãoList = new Requisicao("https://api.coingecko.com/api/v3/coins/list");
        HttpResponse<String> response = clienteHTTP.send(requisiçãoList.getRequest(), HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public HttpResponse<String> buscarMoedaPorID () throws IOException, InterruptedException{
        System.out.println("-----------------BUSCANDO POR ID-----------------");
        System.out.println("Digite o ID da moeda que deseja buscar:");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        String idUsuario = id.trim().toLowerCase();
        String idArrumada = URLEncoder.encode(idUsuario, "UTF-8");
        String url = "https://api.coingecko.com/api/v3/coins/" + idArrumada + "?x-cg-demo-api-key=CG-t8yzBCBXwHvzPa1QaeMsimjo";
        System.out.println(url);
        
        Requisicao requisiçãoID = new Requisicao(url);
        HttpResponse<String> response = clienteHTTP.send(requisiçãoID.getRequest(), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response;
    }
        




}
