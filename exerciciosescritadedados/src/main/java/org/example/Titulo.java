package org.example;
public class Titulo {
    private String nome;
    private String descrição;

    public Titulo(String nome, String descrição){
        this.nome = nome;
        this.descrição = descrição;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrição() {
        return this.descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }
}
