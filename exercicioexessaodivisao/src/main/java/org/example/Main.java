package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner imput = new Scanner(System.in);
        System.out.println("Digite um numero que queira dividir");
        int num1 = imput.nextInt();
        System.out.println("digite o dividendo");
        int num2 = imput.nextInt();

        try {double resultado = num1 / num2;
        System.out.println(resultado);
    } catch (ArithmeticException e) {
        throw new DivisaoPorZeroException("não existe divisão por 0")
        ;
     }

    }
}