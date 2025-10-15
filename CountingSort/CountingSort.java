package CountingSort;

public class CountingSort {

    public static void sort(int[] arr) {
        if (arr.length == 0) return;

        int max = arr[0];
        for (int n : arr) if (n > max) max = n;

        int[] count = new int[max + 1];

        for (int n : arr) count[n]++;

        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[index++] = i;
                count[i]--;
            }
        }
    }
}
