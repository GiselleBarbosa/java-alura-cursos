package br.com.alura.screenmatch.exercicios.consultaGithub;

class ErroConsultaGitHubException extends RuntimeException {

    public ErroConsultaGitHubException(String mensagem) {
        super(mensagem);
    }
}