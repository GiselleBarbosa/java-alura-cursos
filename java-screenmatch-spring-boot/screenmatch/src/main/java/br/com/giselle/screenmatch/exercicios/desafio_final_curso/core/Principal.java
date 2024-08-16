package br.com.giselle.screenmatch.exercicios.desafio_final_curso.core;

import br.com.giselle.screenmatch.exercicios.desafio_final_curso.model.Marcas;
import br.com.giselle.screenmatch.exercicios.desafio_final_curso.model.Modelos;
import br.com.giselle.screenmatch.exercicios.desafio_final_curso.model.Veiculos;
import br.com.giselle.screenmatch.exercicios.desafio_final_curso.services.ConsumoApi;
import br.com.giselle.screenmatch.exercicios.desafio_final_curso.services.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        try {
            var json = consumoApi.obterDados(construirUrlMarcas());
            var marcas = conversor.obterLista(json, Marcas.class);

            if (marcas.isEmpty()) {
                System.out.println("Nenhuma marca encontrada para o tipo de veículo selecionado.");
                return;
            }

            marcas.stream().sorted(Comparator.comparing(Marcas::codigo)).forEach(System.out::println);

            System.out.print("\nDIGITE O CODIGO DA MARCA PARA CONSULTA: ");
            var codigoMarca = scanner.nextLine();

            var jsonModelo = consumoApi.obterDados(construirUrlModelos(codigoMarca));
            var modelosLista = conversor.obterDados(jsonModelo, Modelos.class);

            if (modelosLista.modelos().isEmpty()) {
                System.out.println("Nenhum modelo encontrado para a marca selecionada.");
                return;
            }

            System.out.println("\n>> CONFIRA OS MODELOS DESTA MARCA ");
            modelosLista.modelos().stream()
                    .sorted(Comparator.comparing(Marcas::codigo))
                    .forEach(System.out::println);

            System.out.print("\nDIGITE O NOME DO MODELO QUE DESEJA CONSULTAR: ");
            var nomeVeiculo = scanner.nextLine();

            List<Marcas> modelosFiltrados = modelosLista.modelos().stream()
                    .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                    .collect(Collectors.toList());

            if (modelosFiltrados.isEmpty()) {
                System.out.println("Nenhum modelo encontrado com o nome fornecido.");
                return;
            }

            System.out.println("\n>> MODELOS ENCONTRADOS: ");
            modelosFiltrados.forEach(System.out::println);

            System.out.print("\nDIGITE O CÓDIGO DO MODELO QUE DESEJA CONSULTAR: ");
            var codigoModelo = scanner.nextLine();

            var urlAnos = construirUrlAnos(codigoMarca, codigoModelo);
            json = consumoApi.obterDados(urlAnos);

            List<Marcas> anos = conversor.obterLista(json, Marcas.class);
            if (anos.isEmpty()) {
                System.out.println("Nenhuma avaliação encontrada para o modelo selecionado.");
                return;
            }

            List<Veiculos> veiculos = new ArrayList<>();

            for (var ano : anos) {
                try {
                    var urlVeiculoPorAno = construirUrlVeiculoPorAno(codigoMarca, codigoModelo, ano.codigo());
                    json = consumoApi.obterDados(urlVeiculoPorAno);
                    Veiculos veiculo = conversor.obterDados(json, Veiculos.class);
                    veiculos.add(veiculo);
                } catch (Exception e) {
                    System.out.println("Erro ao buscar dados para o ano " + ano.codigo() + ": " + e.getMessage());
                }
            }

            if (veiculos.isEmpty()) {
                System.out.println("Nenhum veículo encontrado.");
            } else {
                System.out.println("\nTODOS OS VEICULOS FILTROS COM AVALIAÇÃO POR ANO: ");
                veiculos.forEach(System.out::println);
            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a consulta: " + e.getMessage());
        }
    }

    private String construirUrlMarcas() {
        return API_FIPE + tipoVeiculoSelecionado + "/" + MARCAS;
    }

    private String construirUrlModelos(String codigoMarca) {
        return API_FIPE + tipoVeiculoSelecionado + "/" + MARCAS + "/" + codigoMarca + "/modelos";
    }

    private String construirUrlAnos(String codigoMarca, String codigoModelo) {
        return API_FIPE + tipoVeiculoSelecionado + "/" + MARCAS + "/" + codigoMarca + "/modelos" + "/" + codigoModelo + "/anos";
    }

    private String construirUrlVeiculoPorAno(String codigoMarca, String codigoModelo, String anoCodigo) {
        return construirUrlAnos(codigoMarca, codigoModelo) + "/" + anoCodigo;
    }
}
