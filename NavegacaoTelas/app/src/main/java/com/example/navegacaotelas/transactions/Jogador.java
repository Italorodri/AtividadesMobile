package com.example.navegacaotelas.transactions;

public class Jogador {
    private static int contadorId = 0;

    private int id;
    private String nome;
    private String timeAtual;
    private String numero;
    private String nacionalidade;

    public Jogador(String nome, String timeAtual, String numero, String nacionalidade) {
        this.id = contadorId++;

        this.nome = nome;
        this.timeAtual = timeAtual;
        this.numero = numero;
        this.nacionalidade = nacionalidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTimeAtual() {
        return timeAtual;
    }

    public void setTimeAtual(String timeAtual) {
        this.timeAtual = timeAtual;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    @Override
    public String toString() {
        return nome;
    }
}
