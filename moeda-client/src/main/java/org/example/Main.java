package org.example;

import java.io.IOException;
import java.util.Scanner;

import org.example.clientHttpUtil.ClienteHttp;
import org.example.clientHttpUtil.reposnse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, Exception {
        ClienteHttp clienttHttp = new ClienteHttp();
        reposnse response = new reposnse(clienttHttp.listarMoedas());
        response.resultadoLista();


        
        reposnse responseId = new reposnse(clienttHttp.buscarMoedaPorID());
        System.out.println();
        responseId.resultadoId();
        
    }

    
}