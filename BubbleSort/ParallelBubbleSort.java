package BubbleSort;

import java.util.ArrayList;
import java.util.List;

public class ParallelBubbleSort {

    private List<Thread> threads;

    public ParallelBubbleSort() {
        threads = new ArrayList<>();
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void sort(int[] arr, int numThreads) {
        int tamanho_array = arr.length;
        threads.clear(); 

        
        for (int phase = 0; phase < tamanho_array; phase++) {
            int start = phase % 2;

            for (int t = 0; t < numThreads; t++) {
                final int threadIndex = t;

                Thread thread = new Thread(() -> {
                    int tamanho_bloco = (tamanho_array + numThreads - 1) / numThreads;
                    int begin = threadIndex * tamanho_bloco;
                    int end = Math.min(begin + tamanho_bloco, tamanho_array - 1);

                    for (int i = begin + start; i < end; i += 2) {
                        if (arr[i] > arr[i + 1]) {
                            int temp = arr[i];
                            arr[i] = arr[i + 1];
                            arr[i + 1] = temp;
                        }
                    }
                });

                threads.add(thread);
                thread.start();
            }

            
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
