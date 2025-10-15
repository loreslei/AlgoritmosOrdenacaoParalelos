package Utilitarios;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

public class GeradorCSV {

    public static void salvarResultado(String caminhoPasta, String nomeArquivo,
                                       String tipo, int qtdValores, double tempo, int numThreads) {
        try {

            Path pastaPath = Path.of(caminhoPasta);
            if (!Files.exists(pastaPath)) {
                Files.createDirectories(pastaPath);
            }


            Path caminhoCompleto = pastaPath.resolve(nomeArquivo);

            try (FileWriter writer = new FileWriter(caminhoCompleto.toFile(), true)) {
                writer.write(String.format("%s;%d;%.6f;%d%n", tipo, qtdValores, tempo, numThreads));
            }

        } catch (IOException e) {
            System.out.println("Erro ao gravar CSV: " + e.getMessage());
        }
    }
}


