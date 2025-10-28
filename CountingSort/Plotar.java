package CountingSort;

import Utilitarios.GeradorGrafico;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;

public class Plotar {

    public static void main(String[] args) {
        String caminhoCSV = "CountingSort/resultados.csv";

        List<Dado> dados = new ArrayList<>();

        // Lê o CSV e armazena tudo
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split(";");
                if (partes.length < 4) continue;

                String nome = partes[0].trim();
                int tamanho = Integer.parseInt(partes[1].trim());
                double tempo = Double.parseDouble(partes[2].replace(',', '.').trim());
                int threads = Integer.parseInt(partes[3].trim());

                dados.add(new Dado(nome, tamanho, tempo, threads));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
            return;
        }

        // Pega todas as threads diferentes (excluindo Serial 0)
        Set<Integer> threadsSet = new TreeSet<>();
        for (Dado d : dados) {
            if (d.nome.equalsIgnoreCase("Counting Sort Paralelo")) {
                threadsSet.add(d.threads);
            }
        }

        for (int threads : threadsSet) {
            // Filtra os dados paralelos desse grupo
            List<Dado> paralelos = dados.stream()
                    .filter(d -> d.nome.equalsIgnoreCase("Counting Sort Paralelo") && d.threads == threads)
                    .collect(Collectors.toList());

            List<Integer> tamanhos = new ArrayList<>();
            List<Double> temposSerial = new ArrayList<>();
            List<Double> temposParalelo = new ArrayList<>();

            for (Dado p : paralelos) {
                tamanhos.add(p.tamanho);

                // Serial do mesmo tamanho
                Optional<Dado> s = dados.stream()
                        .filter(d -> d.nome.equalsIgnoreCase("Counting Sort Serial") && d.tamanho == p.tamanho)
                        .findFirst();

                temposSerial.add(s.map(d -> d.tempo).orElse(0.0));
                temposParalelo.add(p.tempo);
            }

            int[] tamanhosArray = tamanhos.stream().mapToInt(Integer::intValue).toArray();
            double[] temposSerialArray = temposSerial.stream().mapToDouble(Double::doubleValue).toArray();
            double[] temposParaleloArray = temposParalelo.stream().mapToDouble(Double::doubleValue).toArray();

            String titulo = "Counting Sort - Serial vs Paralelo (" + threads + " Threads)";

            // Exibe na tela
            GeradorGrafico.exibirGrafico(titulo, tamanhosArray, temposSerialArray, temposParaleloArray);

            // Salva como PNG no mesmo diretório do CSV
            salvarGraficoPNG(titulo, tamanhosArray, temposSerialArray, temposParaleloArray, caminhoCSV, threads);
        }
    }

    // Classe auxiliar para armazenar os dados
    static class Dado {
        String nome;
        int tamanho;
        double tempo;
        int threads;

        Dado(String nome, int tamanho, double tempo, int threads) {
            this.nome = nome;
            this.tamanho = tamanho;
            this.tempo = tempo;
            this.threads = threads;
        }
    }

    // Método para salvar o gráfico como PNG no mesmo diretório do CSV
    private static void salvarGraficoPNG(String titulo, int[] tamanhos, double[] temposSerial, double[] temposParalelo, String caminhoCSV, int threads) {
        try {
            File arquivoCSV = new File(caminhoCSV);
            File pasta = arquivoCSV.getParentFile(); // diretório do CSV

            String nomeArquivo = "grafico_" + threads + "_threads.png";
            File arquivoPNG = new File(pasta, nomeArquivo);

            GeradorGrafico panel = new GeradorGrafico(titulo, tamanhos, temposSerial, temposParalelo);
            panel.setSize(1920, 1280);
            panel.doLayout();

            BufferedImage imagem = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = imagem.createGraphics();

            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, panel.getWidth(), panel.getHeight());

            panel.printAll(g2d);
            g2d.dispose();

            ImageIO.write(imagem, "png", arquivoPNG);
            System.out.println("Gráfico salvo em: " + arquivoPNG.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Erro ao salvar gráfico: " + e.getMessage());
        }
    }
}
