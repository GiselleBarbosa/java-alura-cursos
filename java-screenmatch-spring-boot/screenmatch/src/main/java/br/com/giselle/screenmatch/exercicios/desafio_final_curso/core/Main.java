package br.com.giselle.screenmatch.exercicios.desafio_final_curso.core;

import br.com.giselle.screenmatch.services.ConsumoApi;
import br.com.giselle.screenmatch.services.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main {
    private final Scanner scanner = new Scanner(System.in);

    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();

    private static final String API_FIPE = "https://parallelum.com.br/fipe/api/v1/";
    private String tipoVeiculoSelecionado;

    public void exibeMenu() {
        String tipoVeiculoSelecionado = null;

        while (tipoVeiculoSelecionado == null) {
            System.out.println("\n\n########## BEM VINDO(A) AO SISTEMA DE CONSULTA À TABELA FIPE ########## ");
            System.out.print("\n\nESCOLHA UM TIPO DE VEICULO. DIGITE A OPÇÃO DESEJADA: ");
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
    }


}
