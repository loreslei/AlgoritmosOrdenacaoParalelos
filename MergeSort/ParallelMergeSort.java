package MergeSort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort {

    public void sort(int[] arr, int numThreads) {
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        pool.invoke(new MergeSortTask(arr, 0, arr.length - 1));
        pool.shutdown();
    }

    private static class MergeSortTask extends RecursiveAction {
        private final int[] arr;
        private final int inicio;
        private final int fim;
        private static final int LIMITE_SEQUENCIAL = 10_000; // tamanho mínimo para usar threads

        MergeSortTask(int[] arr, int inicio, int fim) {
            this.arr = arr;
            this.inicio = inicio;
            this.fim = fim;
        }

        @Override
        protected void compute() {
            if (fim - inicio < LIMITE_SEQUENCIAL) {
                sortSerial(arr, inicio, fim);
                return;
            }

            int meio = (inicio + fim) / 2;

            MergeSortTask leftTask = new MergeSortTask(arr, inicio, meio);
            MergeSortTask rightTask = new MergeSortTask(arr, meio + 1, fim);

            invokeAll(leftTask, rightTask);
            merge(arr, inicio, meio, fim);
        }

        private void sortSerial(int[] arr, int inicio, int fim) {
            if (inicio < fim) {
                int meio = (inicio + fim) / 2;
                sortSerial(arr, inicio, meio);
                sortSerial(arr, meio + 1, fim);
                merge(arr, inicio, meio, fim);
            }
        }

        private void merge(int[] arr, int inicio, int meio, int fim) {
            int n1 = meio - inicio + 1;
            int n2 = fim - meio;

            int[] L = new int[n1];
            int[] R = new int[n2];

            for (int i = 0; i < n1; i++) L[i] = arr[inicio + i];
            for (int j = 0; j < n2; j++) R[j] = arr[meio + 1 + j];

            int i = 0, j = 0, k = inicio;
            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) arr[k++] = L[i++];
                else arr[k++] = R[j++];
            }
            while (i < n1) arr[k++] = L[i++];
            while (j < n2) arr[k++] = R[j++];
        }
    }
// teste bostinha

}