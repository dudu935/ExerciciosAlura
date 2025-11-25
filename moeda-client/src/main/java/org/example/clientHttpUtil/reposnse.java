package org.example.clientHttpUtil;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class reposnse {
    private List<ListaMoedas> moedas;
    private MoedaIdDTO moedaId;
    Gson json = new GsonBuilder().setPrettyPrinting().create();

    public reposnse(HttpResponse<String> response) throws Exception{
        String body = response.body().trim();
        if (body.startsWith("[")) {
            Type listType = new TypeToken<List<ListaMoedas>>() {}.getType();
            this.moedas = json.fromJson(body, listType);
        } else {
            this.moedaId = json.fromJson(body, MoedaIdDTO.class);
        }
    }

    public void resultadoId() {
        System.out.println("ID: " + moedaId.id() + " | Símbolo: " + moedaId.symbol() + " | Nome: " + moedaId.name());
    }
    
    public void resultadoLista() {
        moedas.stream().forEach(moeda -> {
            System.out.println("ID: " + moeda.id() + " | Símbolo: " + moeda.symbol() + " | Nome: " + moeda.name());
        });
    }
}
