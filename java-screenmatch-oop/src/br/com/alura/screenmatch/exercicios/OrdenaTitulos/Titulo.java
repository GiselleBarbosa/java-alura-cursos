package br.com.alura.screenmatch.exercicios.OrdenaTitulos;

public class Titulo implements Comparable<Titulo> {
    String nome;

    @Override
    public int compareTo(Titulo outroTitulo) {
        return this.nome.compareTo(outroTitulo.nome);
    }
}