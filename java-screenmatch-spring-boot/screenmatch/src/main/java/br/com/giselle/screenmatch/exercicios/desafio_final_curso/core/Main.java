package br.com.giselle.screenmatch.exercicios.desafio_final_curso.core;

import br.com.giselle.screenmatch.services.ConsumoApi;
import br.com.giselle.screenmatch.services.ConverteDados;

import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);

    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();

    private static final String API_FIPE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {    }

}
