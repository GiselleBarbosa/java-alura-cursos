package br.com.alura.screenmatch.exercicios.validaSenha;

public class SenhaInvalidaException extends RuntimeException {

    public SenhaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
