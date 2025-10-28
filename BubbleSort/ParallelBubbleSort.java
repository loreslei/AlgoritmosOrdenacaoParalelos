package BubbleSort;

import java.util.stream.IntStream;

public class ParallelBubbleSort {

    /**
     * Ordena o vetor utilizando o algoritmo Odd-Even Sort em paralelo.
     *
     * @param v           Vetor de inteiros a ser ordenado.
     * @param numThreads  Número de threads a serem utilizadas no stream paralelo.
     */
    public void sort(int[] v, int numThreads) {
        int n = v.length;

        // Define o nível de paralelismo desejado
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(numThreads));

        // O algoritmo precisa de n fases no pior caso
        for (int phase = 0; phase < n; ++phase) {

            if (phase % 2 == 0) {
                // FASE PAR: compara (0,1), (2,3), (4,5), ...
                IntStream.range(0, n / 2)
                        .parallel()
                        .forEach(j -> {
                            int i = j * 2;
                            if (v[i] > v[i + 1]) {
                                swap(v, i, i + 1);
                            }
                        });

            } else {
                // FASE ÍMPAR: compara (1,2), (3,4), (5,6), ...
                IntStream.range(0, (n - 1) / 2)
                        .parallel()
                        .forEach(j -> {
                            int i = j * 2 + 1;
                            if (i + 1 < n && v[i] > v[i + 1]) {
                                swap(v, i, i + 1);
                            }
                        });
            }

            // A barreira implícita do forEach() em stream paralelo
            // garante que a próxima fase só inicia quando todas as threads
            // da fase atual terminarem.
        }
    }

    /**
     * Troca dois elementos de posição em um vetor.
     */
    private static void swap(int[] v, int i, int j) {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }
}
