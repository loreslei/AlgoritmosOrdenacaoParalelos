package BubbleSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelBubbleSort {

    /**
     * Classe interna que representa cada thread de ordenação (nó 1..N-1)
     */
    private static class Worker implements Runnable {
        private final int[] subArray;

        public Worker(int[] subArray) {
            this.subArray = subArray;
        }

        @Override
        public void run() {
            bubbleSort(subArray);
        }

        private void bubbleSort(int[] arr) {
            int n = arr.length;
            boolean trocou;
            do {
                trocou = false;
                for (int i = 0; i < n - 1; i++) {
                    if (arr[i] > arr[i + 1]) {
                        int temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;
                        trocou = true;
                    }
                }
                n--;
            } while (trocou);
        }

        public int[] getSubArray() {
            return subArray;
        }
    }

    /**
     * Método principal que executa a ordenação em paralelo.
     * Pode ser chamado diretamente a partir da main, passando o número de threads desejado.
     */
    public static int[] sort(int[] arr, int numThreads) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        if (numThreads <= 1) {
            bubbleSort(arr);
            return arr;
        }

        int tamanho = arr.length;
        int tamanhoBloco = (int) Math.ceil((double) tamanho / numThreads);

        List<Thread> threads = new ArrayList<>();
        List<Worker> workers = new ArrayList<>();

        // Divide o array entre as threads
        for (int i = 0; i < numThreads; i++) {
            int inicio = i * tamanhoBloco;
            int fim = Math.min(inicio + tamanhoBloco, tamanho);
            if (inicio >= fim) break;

            int[] subArray = Arrays.copyOfRange(arr, inicio, fim);
            Worker worker = new Worker(subArray);
            Thread t = new Thread(worker);

            workers.add(worker);
            threads.add(t);
            t.start();
        }

        // Espera todas as threads finalizarem
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Nó 0 faz a fusão das partes ordenadas
        return mergeSortedParts(workers);
    }

    /**
     * Fusão final (simula a etapa no nó 0)
     */
    private static int[] mergeSortedParts(List<Worker> workers) {
        int[] resultado = workers.get(0).getSubArray();

        for (int i = 1; i < workers.size(); i++) {
            resultado = merge(resultado, workers.get(i).getSubArray());
        }

        return resultado;
    }

    /**
     * Merge de dois arrays ordenados
     */
    private static int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) result[k++] = a[i++];
            else result[k++] = b[j++];
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    /**
     * Versão simples do Bubble Sort (usada se houver apenas 1 thread)
     */
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean trocou;
        do {
            trocou = false;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    trocou = true;
                }
            }
            n--;
        } while (trocou);
    }
}
