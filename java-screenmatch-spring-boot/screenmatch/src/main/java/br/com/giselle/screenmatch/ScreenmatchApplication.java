package br.com.giselle.screenmatch;

import br.com.giselle.screenmatch.models.DadosPokemon;
import br.com.giselle.screenmatch.models.DadosSerie;
import br.com.giselle.screenmatch.services.ConsumoApi;
import br.com.giselle.screenmatch.services.ConverteDados;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    @Value("${OMDB_API_KEY}")
    String apiKey;

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var consumoApi = new ConsumoApi();
        var conversor = new ConverteDados();

        // Chamada da API OmDb
        var jsonSerie = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=" + apiKey);

        System.out.println("\n****** SAÍDA DO JSON COMPLETO DA API OMDB ******");
        System.out.println(jsonSerie);

        DadosSerie dadosSerie = conversor.obterDados(jsonSerie, DadosSerie.class);

        System.out.println("\n****** SAÍDA APÓS CONVERSÃO DA API OMDB ******");
        System.out.println("Título: " + dadosSerie.titulo());
        System.out.println("Total de Temporadas: " + dadosSerie.totalTemporadas());
        System.out.println("Avaliação IMDb: " + dadosSerie.avaliacao());

        // Chamada da API PokeApi
        var jsonPokemon = consumoApi.obterDados("https://pokeapi.co/api/v2/pokemon/mew");

        DadosPokemon dadosPokemon = conversor.obterDados(jsonPokemon, DadosPokemon.class);

        System.out.println("\n****** SAÍDA APÓS CONVERSÃO DA API POKEAPI ******");
        System.out.println(dadosPokemon);

    }
}
