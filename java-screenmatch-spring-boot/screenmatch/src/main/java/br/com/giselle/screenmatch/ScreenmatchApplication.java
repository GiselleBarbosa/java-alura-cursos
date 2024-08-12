package br.com.giselle.screenmatch;
import br.com.giselle.screenmatch.models.DadosEpisodio;
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

        // Chamada da API OmDb
        var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=" + apiKey);

        ConverteDados conversor = new ConverteDados();
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);


        System.out.println("\n" + dados);
        System.out.println("\n****** SAÍDA APÓS CONVERSÃO ******");
        System.out.println("Título: " + dados.titulo());
        System.out.println("Total de Temporadas: " + dados.totalTemporadas());
        System.out.println("Avaliação IMDb: " + dados.avaliacao());

        json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=7&apikey=" + apiKey);
        DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);


        System.out.println("\n" + dadosEpisodio);
        System.out.println("\n****** SAÍDA APÓS CONVERSÃO ******");
        System.out.println("Título: " + dadosEpisodio.titulo());
        System.out.println("Número do episódio: " + dadosEpisodio.numeroEpisodio());
        System.out.println("Avaliação IMDb: " + dadosEpisodio.avaliacao());
        System.out.println("Data de lançamento: " + dadosEpisodio.dataLancamento());
    }
}
