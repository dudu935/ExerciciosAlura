import ClienteHTTP.ClienteHTTP;
import Response.Resposta;

public class main {

    public static void main(String[] args) throws Exception {
        ClienteHTTP clienteHTTP = new ClienteHTTP();

        String url = clienteHTTP.criadorUrl();
        Resposta resposta = new Resposta(clienteHTTP.criadorDeResposta(url));
        resposta.Resultado();



    }
}
