package br.com.giselle.screenmatch.exercicios.desafio_final_curso.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true
)
public record DadosModelos(List<DadosVeiculos> modelos) {
}
