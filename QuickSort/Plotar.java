package QuickSort;

import Utilitarios.GeradorGrafico;

import java.io.*;
import java.util.*;

public class Plotar {

    public static void main(String[] args) {
        String caminhoCSV = "QuickSort/resultados.csv"; 

        List<Integer> tamanhos = new ArrayList<>();
        List<Double> temposSerial = new ArrayList<>();
        List<Double> temposParalelo = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split(";");
                if (partes.length < 4) continue;

                String nomeAlgoritmo = partes[0].trim();
                int tamanho = Integer.parseInt(partes[1].trim());
                double tempo = Double.parseDouble(partes[2].replace(',', '.').trim());

                
                if (nomeAlgoritmo.equalsIgnoreCase("Quick Sort Serial")) {
                    tamanhos.add(tamanho);
                    temposSerial.add(tempo);
                } else if (nomeAlgoritmo.equalsIgnoreCase("Quick Sort Paralelo")) {
                    temposParalelo.add(tempo);
                }
            }

            
            int[] tamanhosArray = tamanhos.stream().mapToInt(Integer::intValue).toArray();
            double[] temposSerialArray = temposSerial.stream().mapToDouble(Double::doubleValue).toArray();
            double[] temposParaleloArray = temposParalelo.stream().mapToDouble(Double::doubleValue).toArray();

            
            GeradorGrafico.exibirGrafico("QuickSort", tamanhosArray, temposSerialArray, temposParaleloArray);

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }
}
