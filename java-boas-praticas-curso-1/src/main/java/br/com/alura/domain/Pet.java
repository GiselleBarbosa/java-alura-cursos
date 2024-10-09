package br.com.alura.domain;

import com.google.gson.JsonObject;

public class Pet {

    private String tipo;
    private String nome;
    private String raca;
    private int idade;
    private String cor;
    private Float peso;

    public Pet(String tipo, String nome, String raca, int idade, String cor, Float peso) {
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.cor = cor;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", idade=" + idade +
                ", cor='" + cor + '\'' +
                ", peso=" + peso +
                '}';
    }
}
