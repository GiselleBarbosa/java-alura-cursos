package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;

import java.io.IOException;
import java.util.Scanner;

public class CadastrarAbrigosCommand implements Command {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {

        try {
            ClientHttpConfiguration client = new ClientHttpConfiguration();
            AbrigoService abrigoService = new AbrigoService(client);

            abrigoService.cadastrarAbrigo(scanner);

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
