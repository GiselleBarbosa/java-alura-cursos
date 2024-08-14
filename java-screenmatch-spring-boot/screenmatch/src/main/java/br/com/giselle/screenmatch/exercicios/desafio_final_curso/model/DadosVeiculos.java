package br.com.giselle.screenmatch.exercicios.desafio_final_curso.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DadosVeiculos(
        @JsonAlias("name") String name,
        @JsonAlias("code") String code
) {
}
