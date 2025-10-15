package QuickSort;

public class ParallelQuickSort {

    public void sort(int[] arr, int numeroThreads) {
        parallelSort(arr, 0, arr.length - 1, numeroThreads);
    }

    private void parallelSort(int[] arr, int inicio, int fim, int threadsDisponiveis) {
        if (inicio < fim) {

            if (threadsDisponiveis <= 1) {
                sortSerial(arr, inicio, fim);
                return;
            }

            int p = particionar(arr, inicio, fim);

            int threadsParaEsquerda = threadsDisponiveis / 2;
            int threadsParaDireita = threadsDisponiveis - threadsParaEsquerda;

            Thread leftThread = new Thread(() -> parallelSort(arr, inicio, p - 1, threadsParaEsquerda));
            Thread rightThread = new Thread(() -> parallelSort(arr, p + 1, fim, threadsParaDireita));

            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sortSerial(int[] arr, int inicio, int fim) {
        if (inicio < fim) {
            int p = particionar(arr, inicio, fim);
            sortSerial(arr, inicio, p - 1);
            sortSerial(arr, p + 1, fim);
        }
    }

    private int particionar(int[] arr, int inicio, int fim) {
        int pivo = arr[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (arr[j] <= pivo) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[fim];
        arr[fim] = temp;
        return i + 1;
    }
}
