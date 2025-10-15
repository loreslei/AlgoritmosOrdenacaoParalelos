package Utilitarios;

import javax.swing.*;
import java.awt.*;

public class GeradorGrafico extends JPanel {

    private final int[] tamanhos;
    private final double[] temposSerial;
    private final double[] temposParalelo;

    public GeradorGrafico(int[] tamanhos, double[] temposSerial, double[] temposParalelo) {
        this.tamanhos = tamanhos;
        this.temposSerial = temposSerial;
        this.temposParalelo = temposParalelo;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int margem = 50;
        int largura = getWidth() - 2 * margem;
        int altura = getHeight() - 2 * margem;

        double maxTempo = Math.max(max(temposSerial), max(temposParalelo));

        g2.drawLine(margem, getHeight() - margem, margem + largura, getHeight() - margem);
        g2.drawLine(margem, margem, margem, getHeight() - margem);

        g2.setColor(Color.BLUE);
        desenharLinha(g2, tamanhos, temposSerial, margem, largura, altura, maxTempo);

        g2.setColor(Color.RED);
        desenharLinha(g2, tamanhos, temposParalelo, margem, largura, altura, maxTempo);

        g2.setColor(Color.BLACK);
        g2.drawString("Serial (azul) x Paralelo (vermelho)", margem, margem - 10);
    }

    private void desenharLinha(Graphics2D g2, int[] xVals, double[] yVals, int margem, int largura, int altura, double maxY) {
        for (int i = 0; i < xVals.length - 1; i++) {
            int x1 = margem + (int) ((double) i / (xVals.length - 1) * largura);
            int y1 = getHeight() - margem - (int) ((yVals[i] / maxY) * altura);
            int x2 = margem + (int) ((double) (i + 1) / (xVals.length - 1) * largura);
            int y2 = getHeight() - margem - (int) ((yVals[i + 1] / maxY) * altura);
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    private double max(double[] arr) {
        double max = arr[0];
        for (double v : arr) if (v > max) max = v;
        return max;
    }

    public static void exibirGrafico(int[] tamanhos, double[] temposSerial, double[] temposParalelo) {
        JFrame frame = new JFrame("Comparativo Serial x Paralelo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GeradorGrafico(tamanhos, temposSerial, temposParalelo));
        frame.pack();
        frame.setVisible(true);
    }
}
