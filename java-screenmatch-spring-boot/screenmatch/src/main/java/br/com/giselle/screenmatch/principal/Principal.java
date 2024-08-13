package br.com.giselle.screenmatch.principal;

import br.com.giselle.screenmatch.models.DadosSerie;
import br.com.giselle.screenmatch.models.DadosTemporada;
import br.com.giselle.screenmatch.models.Episodio;
import br.com.giselle.screenmatch.services.ConsumoApi;
import br.com.giselle.screenmatch.services.ConverteDados;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Principal {

    private final Scanner scanner = new Scanner(System.in);

    @Value("${OMDB_API_KEY}")
    private String apiKey;

    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();

    private static final String ENDERECO = "https://www.omdbapi.com/?t=";

    public void exibeMenu() {
        System.out.println("\nDIGITE O NOME DA SÉRIE DESEJADA: ");
        var nomeSerie = scanner.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&apikey=" + apiKey);

        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

        /* System.out.println(dados);*/

        List<DadosTemporada> temporadas = new ArrayList<>();

        try {
            for (int i = 1; i <= dados.totalTemporadas(); i++) {
                json = consumoApi.obterDados(ENDERECO + nomeSerie
                        .replace(" ", "+")
                        + "&season="
                        + i
                        + "&apikey="
                        + apiKey);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            /*temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));*/

       /* List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .toList();*/

     /*   System.out.println("\n******TOP 10 EPISODIOS******");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primeiro filtro(N/A) " + e))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .peek(e -> System.out.println("Ordenação " + e))
                .limit(10)
                .peek(e -> System.out.println("Limitando a 10 items " + e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Mapeando e alterando para UPPERCASE " + e))
                .forEach(System.out::println);*/

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(t -> t.episodios().stream()
                            .map(d -> new Episodio(t.numero(), d))
                    ).collect(Collectors.toList());

            episodios.forEach(System.out::println);

            System.out.println("\nDIGITE O NOME DO EPISÓDIO DESEJADO: ");
            var trechoDoTitulo = scanner.nextLine();
            Optional<Episodio> episodioBuscado = episodios.stream()
                    .filter(e -> e.getTitulo()
                            .toUpperCase()
                            .contains(trechoDoTitulo.toUpperCase()))
                    .findFirst();

            try {
                if (!episodioBuscado.isEmpty()) {
                    System.out.println("\nEpisódio: [" + episodioBuscado.get().getTitulo() + "] encontrado com sucesso!");
                    System.out.println("\nTemporada: " + episodioBuscado.get().getTemporada());
                } else {
                    System.out.println("EPISÓDIO NÃO ENCONTRADO.");

                }
            } catch (NoSuchElementException ex) {
                System.out.println("ERRO INESPERADO AO BUSCAR O EPISODIO: " + episodioBuscado.get().getTitulo());
            }

        } catch (NullPointerException ex) {
            System.out.println("HOUVE UM ERRO AO BUSCAR A SERIE.");
        }

        /*System.out.println("A partir de que anos você deseja buscar os episódios? ");
        var ano = scanner.nextInt();
        scanner.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "\n Temporada: " + e.getTemporada() +
                                "\n Episódio: " + e.getTitulo() +
                                "\n Avaliação: " + e.getNota() +
                                "\n Data de lançamento: " + e.getDataLancamento().format(formatador)));
*/
        scanner.close();
    }
}