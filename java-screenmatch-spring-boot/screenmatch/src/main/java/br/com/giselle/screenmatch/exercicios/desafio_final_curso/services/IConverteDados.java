package br.com.giselle.screenmatch.exercicios.desafio_final_curso.services;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);
}
