package br.com.giselle.screenmatch.exercicios.desafio_final_curso;

import br.com.giselle.screenmatch.exercicios.desafio_final_curso.core.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiFipeApplication implements CommandLineRunner {
    @Autowired
    private Main main;

    public static void main(String[] args) {
        SpringApplication.run(ApiFipeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        main.exibeMenu();
    }
}
