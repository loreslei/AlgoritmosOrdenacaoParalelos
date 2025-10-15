package MergeSort;

public class ParallelMergeSort {

    public void sort(int[] arr, int numeroThreads) {
        parallelSort(arr, 0, arr.length - 1, numeroThreads);
    }

    private void parallelSort(int[] arr, int inicio, int fim, int threadsDisponiveis) {
        if (inicio < fim) {
            if (threadsDisponiveis <= 1) {
                sortSerial(arr, inicio, fim);
                return;
            }

            int meio = (inicio + fim) / 2;
            int threadsParaEsquerda = threadsDisponiveis / 2;
            int threadsParaDireita = threadsDisponiveis - threadsParaEsquerda;

            Thread leftThread = new Thread(() -> parallelSort(arr, inicio, meio, threadsParaEsquerda));
            Thread rightThread = new Thread(() -> parallelSort(arr, meio + 1, fim, threadsParaDireita));

            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            merge(arr, inicio, meio, fim);
        }
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
