package BubbleSort;

import java.util.ArrayList;
import java.util.List;

public class ParallelBubbleSort {

    private static class PontoDeEncontro {
        private final int totalDeThreads;
        private int threadsEsperando = 0;

        public PontoDeEncontro(int totalDeThreads) {
            this.totalDeThreads = totalDeThreads;
        }

        public synchronized void esperar() {
            threadsEsperando++;

            if (threadsEsperando < totalDeThreads) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                threadsEsperando = 0;
                notifyAll();
            }
        }
    }


    private static class TarefaDeOrdenacao implements Runnable {
        private final int[] arr;
        private final int inicio, fim, tamanhoTotal;
        private final PontoDeEncontro pontoDeEncontro;

        public TarefaDeOrdenacao(int[] arr, int inicio, int fim, int tamanhoTotal, PontoDeEncontro pontoDeEncontro) {
            this.arr = arr;
            this.inicio = inicio;
            this.fim = fim;
            this.tamanhoTotal = tamanhoTotal;
            this.pontoDeEncontro = pontoDeEncontro;
        }

        @Override
        public void run() {
            for (int fase = 0; fase < tamanhoTotal; fase++) {
                
                int inicioDaFase = fase % 2;
                int i_start = this.inicio;
                if (i_start % 2 != inicioDaFase) {
                    i_start++;
                }
                
                for (int i = i_start; i < this.fim - 1; i += 2) {
                    if (arr[i] > arr[i + 1]) {
                        int temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;
                    }
                }
                
                pontoDeEncontro.esperar();
            }
        }
    }

    public void sort(int[] arr, int numThreads) {
        if (numThreads <= 0 || arr == null || arr.length <= 1) {
            return;
        }

        int threadsReais = Math.min(numThreads, arr.length / 2);
        if (threadsReais == 0) threadsReais = 1;

        final PontoDeEncontro pontoDeEncontro = new PontoDeEncontro(threadsReais);
        final List<Thread> threads = new ArrayList<>();
        int tamanho_array = arr.length;

        for (int i = 0; i < threadsReais; i++) {
            int tamanho_bloco = (tamanho_array + threadsReais - 1) / threadsReais;
            int inicio = i * tamanho_bloco;
            int fim = Math.min(inicio + tamanho_bloco, tamanho_array);
            
            TarefaDeOrdenacao tarefa = new TarefaDeOrdenacao(arr, inicio, fim, tamanho_array, pontoDeEncontro);
            threads.add(new Thread(tarefa));
        }

        for (Thread thread : threads) {
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