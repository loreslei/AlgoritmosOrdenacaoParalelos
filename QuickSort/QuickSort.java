package QuickSort;

public class QuickSort {
    public static void sort(int[] arr, int inicio, int fim) {
        if (inicio < fim) {
            int p = particionar(arr, inicio, fim);
            sort(arr, inicio, p - 1);
            sort(arr, p + 1, fim);
        }
    }

    private static int particionar(int[] arr, int inicio, int fim) {
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

