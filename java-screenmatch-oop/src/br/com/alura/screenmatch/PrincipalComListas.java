package br.com.alura.screenmatch;

import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;
import br.com.alura.screenmatch.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrincipalComListas {
    public static void main(String[] args) {
        Filme armageddon = new Filme("Armageddon", 1998);
        armageddon.avalia(8);

        Filme batman = new Filme("Batman", 1998);
        armageddon.avalia(7);

        Serie greysAnatomy = new Serie("Grey's Anatomy", 2000);
        armageddon.avalia(6);

        Filme aOrigem = new Filme("A Origem", 2010);
        armageddon.avalia(9);

        Filme favorito = new Filme("The Matrix", 1999);
        favorito.avalia(10);

        Filme outro = new Filme("John Wick", 2014);
        outro.avalia(9);

        Serie serie = new Serie("La Casa de Papel", 2017);

        List<Titulo> lista = new ArrayList<>();
        lista.add(armageddon);
        lista.add(batman);
        lista.add(aOrigem);
        lista.add(greysAnatomy);
        lista.add(favorito);
        lista.add(outro);
        lista.add(serie);

        for (Titulo item : lista) {
            System.out.println(item.getNome());
            if (item instanceof Filme filme && filme.getClassificacao() > 2) {
                System.out.println("Classificação " + filme.getClassificacao());
            }
        }

       List<String> buscaPorArtista = new ArrayList<>();

        buscaPorArtista.add("Will Smith");
        buscaPorArtista.add("Keanu Reeves");
        buscaPorArtista.add("Sandra Bullock");
        buscaPorArtista.add("Angelina Jolie");
        buscaPorArtista.add("Leonardo DiCaprio");

        System.out.println("==== Antes da Ordenação ====");
        System.out.println(buscaPorArtista);

        System.out.println("==== Depois da Ordenação sort()==== ");
        Collections.sort(buscaPorArtista);
        System.out.println(buscaPorArtista);

        Collections.sort(lista);
        System.out.println(lista);

        System.out.println("");
        System.out.println("==== Uso do sort comparator para ordenar de acordo algum criterio ====");
        lista.sort(Comparator.comparing(Titulo::getAnoDeLancamento));

        System.out.println(lista);

    }
}
