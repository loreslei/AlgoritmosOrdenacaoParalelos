package CountingSort;

import java.util.Arrays;

public class ParallelCountingSort {

    public void sort(int[] arr, int numeroThreads) {
        if (arr.length == 0) return;

        int max = Arrays.stream(arr).max().orElse(0);

        int[][] counts = new int[numeroThreads][max + 1];
        int tamanho = arr.length;
        int bloco = (int) Math.ceil((double) tamanho / numeroThreads);

        Thread[] threads = new Thread[numeroThreads];

        // Cada thread conta seu bloco
        for (int t = 0; t < numeroThreads; t++) {
            final int index = t;
            int inicio = t * bloco;
            int fim = Math.min(inicio + bloco, tamanho);

            threads[t] = new Thread(() -> {
                int[] localCount = counts[index];
                for (int i = inicio; i < fim; i++) {
                    localCount[arr[i]]++;
                }
            });
            threads[t].start();
        }

        // Espera todas terminarem
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Soma as contagens em um vetor global
        int[] totalCount = new int[max + 1];
        for (int t = 0; t < numeroThreads; t++) {
            for (int i = 0; i <= max; i++) {
                totalCount[i] += counts[t][i];
            }
        }

        // Reconstrói o array ordenado
        int idx = 0;
        for (int i = 0; i <= max; i++) {
            while (totalCount[i]-- > 0) {
                arr[idx++] = i;
            }
        }
    }
}
