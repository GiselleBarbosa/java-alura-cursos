package br.com.giselle.screenmatch.principal;

import br.com.giselle.screenmatch.models.DadosSerie;
import br.com.giselle.screenmatch.models.DadosTemporada;
import br.com.giselle.screenmatch.services.ConsumoApi;
import br.com.giselle.screenmatch.services.ConverteDados;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private final Scanner scanner = new Scanner(System.in);

    @Value("${OMDB_API_KEY}")
    private String apiKey;

    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();

    private static final String ENDERECO = "https://www.omdbapi.com/?t=";

    public void exibeMenu() {
        System.out.println("Digite o nome da série desejada: ");
        var nomeSerie = scanner.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&apikey=" + apiKey);

        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + "&apikey=" + apiKey);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(System.out::println);
        scanner.close();
    }
}