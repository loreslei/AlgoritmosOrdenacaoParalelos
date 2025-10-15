package CountingSort;

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

                Thread tSerial = new Thread(() -> {
                    double tempo = AnaliseDesempenho.analisarAlgoritmo(
                            "Counting Sort Serial", arr_seq,
                            () -> CountingSort.sort(arr_seq)
                    );
                    tempoSerial.set(tempo);
                    GeradorCSV.salvarResultado("CountingSort", "resultados.csv",
                            "Counting Sort Serial", qtdValores, tempo, 0);
                });

                Thread tParalelo = new Thread(() -> {
                    ParallelCountingSort pcs = new ParallelCountingSort();
                    double tempo = AnaliseDesempenho.analisarAlgoritmo(
                            "Counting Sort Paralelo", arr_paralelo,
                            () -> pcs.sort(arr_paralelo, numeroThreads)
                    );
                    tempoParalelo.set(tempo);
                    GeradorCSV.salvarResultado("CountingSort", "resultados.csv",
                            "Counting Sort Paralelo", qtdValores, tempo, numeroThreads);
                });

                tSerial.start();
                tParalelo.start();

                try {
                    tSerial.join();
                    tParalelo.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.printf("Diferença de tempo: %.6f segundos%n",
                        Math.abs(tempoSerial.get() - tempoParalelo.get()));
                System.out.println("--------------------------------------------\n");
            }
        }
    }
}
