package org.example;
import java.io.IOException;

import org.example.ClienteHttpUtil.CLienteHTTP;
import org.example.ClienteHttpUtil.Resposta;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        CLienteHTTP cliente = new CLienteHTTP();
        Resposta response = new Resposta(cliente.criadorDeResposta());
        response.Resultado();
    }
}