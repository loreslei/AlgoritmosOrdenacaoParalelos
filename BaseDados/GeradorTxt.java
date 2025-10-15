package BaseDados;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeradorTxt {

    public static void gerarArquivo(String nomeArquivo, int tamanho) {
        Random rand = new Random();
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            for (int i = 0; i < tamanho; i++) {
                writer.write(rand.nextInt(10_000_000) + "\n"); // cada número em uma linha
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        gerarArquivo("BaseDados/base1.txt", 1_000);
        gerarArquivo("BaseDados/base2.txt", 10_000);
        gerarArquivo("BaseDados/base3.txt", 100_000);
        gerarArquivo("BaseDados/base4.txt", 200_000);
        gerarArquivo("BaseDados/base5.txt", 100_000_000);

        System.out.println("Arquivos gerados com sucesso!");
    }
}
