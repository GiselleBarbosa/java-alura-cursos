package br.com.alura;

import java.util.Scanner;

public class AdopetConsoleApplication {
    public static void main(String[] args) {

        CommandExecutor execute = new CommandExecutor();

        Scanner scanner = new Scanner(System.in);

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
                System.out.println("1 -> Listar abrigos cadastrados");
                System.out.println("2 -> Cadastrar novo abrigo");
                System.out.println("3 -> Listar pets do abrigo");
                System.out.println("4 -> Importar pets do abrigo");
                System.out.println("5 -> Sair");

                String textoDigitado = scanner.nextLine();

                opcaoEscolhida = Integer.parseInt(textoDigitado);

                if (opcaoEscolhida == 1) {
                    execute.executeCommand(new ListarAbrigosCommand());
                } else if (opcaoEscolhida == 2) {
                    execute.executeCommand(new CadastrarAbrigosCommand());
                } else if (opcaoEscolhida == 3) {
                    execute.executeCommand(new ListarPetsDoAbrigoCommand());
                } else if (opcaoEscolhida == 4) {
                    execute.executeCommand(new CadastrarPetCommand());
                } else if (opcaoEscolhida == 5) {
                    break;
                } else {
                    System.out.println("NÚMERO INVÁLIDO!");
                    opcaoEscolhida = 0;
                }
            }
            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}