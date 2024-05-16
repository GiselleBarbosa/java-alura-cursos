public class main {
    public static void main(String[] args) {
        ConsultaCep consultaCep = new ConsultaCep();

        Endereco novoEndereco =  consultaCep.buscaEndereco("02810000");
        System.out.println(novoEndereco);
    }
}
