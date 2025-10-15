package QuickSort;

import Utilitarios.AnaliseDesempenho;
import Utilitarios.GeradorCSV;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static Utilitarios.LeitorTxt.lerInteiros;

public class Main {

    public static void main(String[] args) {

        int[] qtdsThreads = {2, 6, 10};

        for (int j = 0; j < qtdsThreads.length; j++) {
            int numeroThreads = qtdsThreads[j];

            for (int i = 1; i <= 5; i++) {
                String caminho = "BaseDados/base" + i + ".txt";

                int[] arr_seq = lerInteiros(caminho);
                int[] arr_paralelo = Arrays.copyOf(arr_seq, arr_seq.length);

                System.out.println("Execução " + i + " com " + numeroThreads + " threads");

                int qtdValores = arr_seq.length;

                AtomicReference<Double> tempoSerial = new AtomicReference<>(0.0);
                AtomicReference<Double> tempoParalelo = new AtomicReference<>(0.0);

                // Execução Serial
                Thread tSerial = new Thread(() -> {
                    double tempo = AnaliseDesempenho.analisarAlgoritmo(
                            "Quick Sort Serial", arr_seq,
                            () -> QuickSort.sort(arr_seq, 0, arr_seq.length - 1)
                    );
                    tempoSerial.set(tempo);
                    GeradorCSV.salvarResultado("QuickSort", "resultados.csv",
                            "Quick Sort Serial", qtdValores, tempo, 0);
                });

                // Execução Paralela
                Thread tParalelo = new Thread(() -> {
                    ParallelQuickSort pqs = new ParallelQuickSort();
                    double tempo = AnaliseDesempenho.analisarAlgoritmo(
                            "Quick Sort Paralelo", arr_paralelo,
                            () -> pqs.sort(arr_paralelo, numeroThreads)
                    );
                    tempoParalelo.set(tempo);
                    GeradorCSV.salvarResultado("QuickSort", "resultados.csv",
                            "Quick Sort Paralelo", qtdValores, tempo, numeroThreads);
                });

                tSerial.start();
                tParalelo.start();

                try {
                    tSerial.join();
                    tParalelo.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.printf("Diferença de tempo: %.4f segundos%n",
                        Math.abs(tempoSerial.get() - tempoParalelo.get()));
                System.out.println("--------------------------------------------\n");
            }
        }
    }
}
