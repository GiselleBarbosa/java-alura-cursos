package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AbrigoService {

    private ClientHttpConfiguration client;

    public AbrigoService(ClientHttpConfiguration client) {
        this.client = client;
    }

    public void cadastrarAbrigo(Scanner scanner) throws IOException, InterruptedException {
        try {
            System.out.println("Digite o nome do abrigo:");
            String nome = scanner.nextLine();
            System.out.println("Digite o endereco do abrigo:");
            String endereco = scanner.nextLine();
            System.out.println("Digite o email do abrigo:");
            String email = scanner.nextLine();
            System.out.println("Digite o telefone do abrigo:");
            String telefone = scanner.nextLine();

            Abrigo abrigoDados = new Abrigo(nome, endereco, email, telefone);

            String uri = "http://localhost:8080/abrigos";

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(abrigoDados);

            HttpResponse<String> response = client.realizarRequisicoesPost(uri, abrigoDados);

            int statusCode = response.statusCode();
            String responseBody = response.body();
            if (statusCode == 200 || statusCode == 201) {
                System.out.println("Abrigo cadastrado com sucesso!");
                System.out.println(responseBody);
            } else {
                System.out.println("Erro ao cadastrar o abrigo:");
                System.out.println("Status: " + statusCode);
                System.out.println(responseBody);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void listarAbrigosCadastrados() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/abrigos";

        HttpResponse<String> response = client.realizarRequisicoesGet(uri);

        String responseBody = response.body();

        Abrigo[] abrigos = new ObjectMapper().readValue(responseBody, Abrigo[].class);
        List<Abrigo> abrigosList = Arrays.stream(abrigos).toList();

        if (abrigosList.isEmpty()) {
            System.out.println("Não existem abrigos cadastrados!");

        } else {
            mostrarAbrigos(abrigosList);
        }
    }

    private static void mostrarAbrigos(List<Abrigo> abrigosList) {
        System.out.println("Abrigos cadastrados:");
        for (Abrigo abrigo : abrigosList) {
            long id = abrigo.getId();
            String nome = abrigo.getNome();
            System.out.println(id + " - " + nome);
        }
    }
}