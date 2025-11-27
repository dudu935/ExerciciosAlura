package ClienteHttpUtil;

import DTO.BooksResponse;
import DTO.volumeInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.http.HttpResponse;

public class Resposta {
    private BooksResponse booksResponse;
    Gson json = new GsonBuilder().setPrettyPrinting().create();

    public Resposta(HttpResponse<String> response) throws Exception{
        booksResponse = json.fromJson(response.body(), BooksResponse.class);
    }

    public void Resultado() {
       for (BooksResponse.Item livro : booksResponse.items()) {
           volumeInfo volumeInfo = livro.volumeInfo();
           System.out.println("Titulo:" + volumeInfo.title());
           System.out.println("Descricao:" + volumeInfo.description());
           System.out.println("autor: " + volumeInfo.authors());
       }
    }
}
