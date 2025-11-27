package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonReader reader = new JsonReader(new InputStreamReader(Main.class.getResourceAsStream("/pessoa.json")));
        Pessoa pessoa = gson.fromJson(reader, Pessoa.class);
        System.out.println(pessoa);
        
        JsonReader readerLivro = new JsonReader(new InputStreamReader(Main.class.getResourceAsStream("/livro.json")));
        LivroDTO livro = gson.fromJson(readerLivro, LivroDTO.class);
        Livro livroObj = new Livro(livro.titulo(), livro.autor(),
                new Editora(livro.editora().nome(), livro.editora().endereco()));
        System.out.println(livroObj);
    }
}