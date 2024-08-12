package br.com.alura.screenmatch.calculos;

import br.com.alura.screenmatch.modelos.Titulo;

public class CalculadoraDeTempo {
    private int tempoTotal;

    public int getTempoTotal() {
        return tempoTotal;
    }

 /*   public void incluir(Filme filme) {
        tempoTotal += filme.getDuracaoEmMinutos();
    }
    public void incluir(Serie serie) {
        tempoTotal += serie.getDuracaoEmMinutos();
    }*/
    public void incluir(Titulo titulo) {
        System.out.println("Adicionando duração em minutos de " + titulo);
        this.tempoTotal += titulo.getDuracaoEmMinutos();
    }
}
