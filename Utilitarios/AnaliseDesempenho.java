package Utilitarios;

public class AnaliseDesempenho {

    public static double analisarAlgoritmo(String nomeAlgoritmo, int[] arr, Runnable algoritmo) {
        long inicio = System.nanoTime();
        algoritmo.run(); 
        long fim = System.nanoTime();

        double tempoSegundos = (fim - inicio) / 1e9;
        System.out.printf("%s -> Tempo de execução: %.5f segundos%n", nomeAlgoritmo, tempoSegundos);
        return tempoSegundos;
    }
}
