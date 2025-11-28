package org.example;

public class DivisaoPorZeroException extends RuntimeException {
    public DivisaoPorZeroException(String menssagem) {
        super("Não existe divisão por zero");
    }

}
