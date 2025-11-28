import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("digite sua senha");
        Scanner imput = new Scanner(System.in);
        String senha = imput.nextLine();
        boolean validaSenha = senha.length() >= 8;

        try {
            if (!validaSenha) {
                throw new ValidadorSenhaException("Senha invalida");
            } else {
                System.out.println("Senha correta");
            }
        } catch (ValidadorSenhaException e) {
            System.out.println(e.getMessage());
        }
    }
}
