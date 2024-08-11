/* import java.util.Locale; */
import java.util.Scanner;

public class Leitura {
    public static void main(String[] args) {
    /* Locale.setDefault(Locale.US);*/
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do filme: ");
        String nomeFilme = scanner.next();
        System.out.print("Qual o ano de lançamento: ");
        int anoFilme = scanner.nextInt();
        System.out.print("Nota do filme: " );
        double avaliacaoFilme = scanner.nextDouble();
        System.out.println(
                "Nome do filme: "
                + nomeFilme
                + " | "
                + "Ano do filme: "
                + anoFilme
                + " | "
                + "Avaliação do filme: "
                + avaliacaoFilme );
        scanner.close();
    }
}
