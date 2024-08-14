package br.com.giselle.screenmatch.exercicios.desafio_final_curso;

import br.com.giselle.screenmatch.ScreenmatchApplication;
import br.com.giselle.screenmatch.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class ApiFipeApplication implements CommandLineRunner {
    @Autowired
    private Principal principal;

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        principal.exibeMenu();
    }
}
