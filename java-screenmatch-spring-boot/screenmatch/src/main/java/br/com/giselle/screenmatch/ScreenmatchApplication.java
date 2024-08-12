package br.com.giselle.screenmatch;

import br.com.giselle.screenmatch.services.ConsumoApi;
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

        // Chamada da API OmDb
        var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&Season=1&apikey=" + apiKey);
        System.out.println(json);

        // Chamada da API PokeApi
        var json2 = consumoApi.obterDados("https://pokeapi.co/api/v2/pokemon/mew");
        System.out.println(json2);

    }
}
