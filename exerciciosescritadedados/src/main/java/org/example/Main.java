package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        Titulo titulo = new Titulo("interestelar", "filme de espaço");
        Veiculo veiculo = new Veiculo("modeloX", "marcaY", 2020);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        

        String tituloJson = gson.toJson(titulo);
        try (FileWriter writer = new FileWriter("titulo.json")){
            gson.toJson(tituloJson, writer);
            System.out.println(tituloJson);}
        catch (IOException e){ 
            e.printStackTrace();
        }

        String veiculoJson = gson.toJson(veiculo);
        try (FileWriter writer = new FileWriter("veiculo.json")){
            gson.toJson(veiculoJson, writer);
            System.out.println(veiculoJson);}
        catch (IOException e){ 
            e.printStackTrace();
        }   
    }
}