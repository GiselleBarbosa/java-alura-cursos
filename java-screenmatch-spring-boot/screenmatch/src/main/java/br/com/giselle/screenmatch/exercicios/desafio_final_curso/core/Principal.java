package br.com.giselle.screenmatch.exercicios.desafio_final_curso.core;

import br.com.giselle.screenmatch.exercicios.desafio_final_curso.model.DadosModelos;
import br.com.giselle.screenmatch.exercicios.desafio_final_curso.model.DadosVeiculos;
import br.com.giselle.screenmatch.exercicios.desafio_final_curso.services.ConsumoApi;
import br.com.giselle.screenmatch.exercicios.desafio_final_curso.services.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Principal {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();

    private static final String API_FIPE = "https://parallelum.com.br/fipe/api/v1/";
    private static final String MARCAS = "marcas";
    private String tipoVeiculoSelecionado;

    public void exibeMenu() {

        while (tipoVeiculoSelecionado == null) {
            System.out.println("\n### BEM VINDO(A) AO SISTEMA DE CONSULTA À TABELA FIPE ###");
            System.out.print("\nESCOLHA UM TIPO DE VEICULO. DIGITE A OPÇÃO DESEJADA: ");
            System.out.println("\n[1] Carros \n[2] Motos \n[3] Caminhoes");

            var tiposVeiculo = scanner.nextLine();

            switch (tiposVeiculo) {
                case "1":
                    tipoVeiculoSelecionado = "carros";
                    break;
                case "2":
                    tipoVeiculoSelecionado = "motos";
                    break;
                case "3":
                    tipoVeiculoSelecionado = "caminhoes";
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, digite 1, 2 ou 3.");
            }
        }

        System.out.println("Você selecionou: " + tipoVeiculoSelecionado);

        var json = consumoApi.obterDados(API_FIPE + this.tipoVeiculoSelecionado + "/" + MARCAS);
        var marcas = conversor.obterLista(json, DadosVeiculos.class);

        marcas.stream().sorted(Comparator.comparing(DadosVeiculos::codigo)).forEach(System.out::println);

        System.out.print("\nDIGITE O CODIGO DA MARCA PARA CONSULTA: ");

        var codigoMarca = scanner.nextLine();

        var jsonModelo = consumoApi.obterDados(API_FIPE + this.tipoVeiculoSelecionado + "/" + MARCAS + "/" + codigoMarca + "/modelos");
        var modelosLista = conversor.obterDados(jsonModelo, DadosModelos.class);

        System.out.println("\nCONFIRA OS MODELOS DESTA MARCA: ");
        modelosLista.modelos().stream()
                .sorted(Comparator.comparing(DadosVeiculos::codigo))
                .forEach(System.out::println);

        System.out.println("\nDIGITE O NOME DO MODELO QUE DESEJA CONSULTAR: ");

        var nomeVeiculo = scanner.nextLine();

        List<DadosVeiculos> modelosFiltrados = modelosLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nMODELOS ENCONTRADOS: ");

        modelosFiltrados.forEach(System.out::println);

    }
}
