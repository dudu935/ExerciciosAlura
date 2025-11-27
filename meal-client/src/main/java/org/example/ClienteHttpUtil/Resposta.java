package org.example.ClienteHttpUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.http.HttpResponse;

import org.example.DTO.MealsDTO;

public class Resposta {
    public MealsDTO meals;
    Gson json = new GsonBuilder().setPrettyPrinting().create();

    public Resposta(HttpResponse response) {
        System.out.println("Corpo da resposta: " + response.body());
        this.meals = json.fromJson(response.body().toString(), MealsDTO.class);
    }

    public void Resultado() {
        for (MealsDTO.meals meal : meals.meals()) {
            System.out.println("Nome da receita: " + meal.strMeal());
            System.out.println("Categoria: " + meal.strCategory());
            System.out.println("Area: " + meal.strArea());
            System.out.println("Instrucoes: " + meal.strInstructions());
            System.out.println("Link do video: " + meal.strYoutube());
            System.out.println("--------------------------------------------------");
        }
    }

}
