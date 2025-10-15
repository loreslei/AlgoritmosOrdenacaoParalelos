package Utilitarios;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LeitorTxt {

    public static int[] lerInteiros(String caminhoArquivo) {
        List<Integer> numeros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    try {
                        numeros.add(Integer.parseInt(linha));
                    } catch (NumberFormatException e) {
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + caminhoArquivo);
            e.printStackTrace();
        }


        int[] array = new int[numeros.size()];
        for (int i = 0; i < numeros.size(); i++) {
            array[i] = numeros.get(i);
        }

        return array;
    }
}