package br.com.giselle.screenmatch.exercicios.desafio_final_curso.core;

import br.com.giselle.screenmatch.exercicios.desafio_final_curso.services.ConsumoApi;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main {
    private final Scanner scanner = new Scanner(System.in);

    private final ConsumoApi consumoApi = new ConsumoApi();

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


        var apiEndereco = consumoApi.obterDados(API_FIPE + this.tipoVeiculoSelecionado + "/" + MARCAS);
        System.out.println(apiEndereco);
    }


}
