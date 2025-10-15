package MergeSort;
public class MergeSort {
    public static void sort(int[] arr, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            sort(arr, inicio, meio);
            sort(arr, meio + 1, fim);
            merge(arr, inicio, meio, fim);
        }
    }

    private static void merge(int[] arr, int inicio, int meio, int fim) {
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
