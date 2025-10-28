package Utilitarios;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class GeradorGrafico extends JPanel {

    private final String algoritmo;
    private final int[] tamanhos;
    private final double[] temposSerial;
    private final double[] temposParalelo;

    public GeradorGrafico(String algoritmo, int[] tamanhos, double[] temposSerial, double[] temposParalelo) {
        this.algoritmo = algoritmo;
        this.tamanhos = tamanhos;
        this.temposSerial = temposSerial;
        this.temposParalelo = temposParalelo;
        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int margem = 70;
        int largura = getWidth() - 2 * margem;
        int altura = getHeight() - 2 * margem;

        // Eixos
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(margem, getHeight() - margem, margem + largura, getHeight() - margem);
        g2.drawLine(margem, margem, margem, getHeight() - margem);

        // Título
        g2.setFont(new Font("SansSerif", Font.BOLD, 20));
        String titulo = "Desempenho - " + algoritmo + " (Serial x Paralelo)";
        g2.drawString(titulo, getWidth() / 2 - g2.getFontMetrics().stringWidth(titulo) / 2, 40);

        // Escala
        double maxTempo = Math.max(max(temposSerial), max(temposParalelo));

        // Valores nos eixos
        g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g2.setColor(Color.DARK_GRAY);

        // Eixo X
        for (int i = 0; i < tamanhos.length; i++) {
            int x = margem + (int) ((double) i / (tamanhos.length - 1) * largura);
            int y = getHeight() - margem;
            g2.drawLine(x, y - 5, x, y + 5);
            g2.drawString(String.valueOf(tamanhos[i]), x - 10, y + 20);
        }

        // Eixo Y
        for (int i = 0; i <= 5; i++) {
            int y = getHeight() - margem - (i * altura / 5);
            double valor = (maxTempo / 5) * i;
            g2.drawLine(margem - 5, y, margem + 5, y);
            g2.drawString(String.format("%.2f", valor), margem - 55, y + 5);
        }

        // Linhas
        g2.setStroke(new BasicStroke(2.5f));
        g2.setColor(new Color(253, 197, 245)); // Rosa
        desenharLinha(g2, tamanhos, temposSerial, margem, largura, altura, maxTempo);

        g2.setColor(new Color(114, 221, 247)); // Azul
        desenharLinha(g2, tamanhos, temposParalelo, margem, largura, altura, maxTempo);

        // Legenda
        int lx = getWidth() - 220;
        int ly = margem + 20;
        g2.setFont(new Font("SansSerif", Font.PLAIN, 14));

        g2.setColor(new Color(253, 197, 245));
        g2.fillRect(lx, ly, 15, 15);
        g2.setColor(Color.BLACK);
        g2.drawString("Serial", lx + 25, ly + 12);

        g2.setColor(new Color(114, 221, 247));
        g2.fillRect(lx, ly + 25, 15, 15);
        g2.setColor(Color.BLACK);
        g2.drawString("Paralelo", lx + 25, ly + 37);

        // Rótulos dos eixos
        g2.setFont(new Font("SansSerif", Font.BOLD, 14));
        g2.drawString("Tamanho do Vetor", getWidth() / 2 - 60, getHeight() - 20);
        g2.rotate(-Math.PI / 2);
        g2.drawString("Tempo de Execução (s)", -getHeight() / 2 - 70, 30);
        g2.rotate(Math.PI / 2);
    }

    private void desenharLinha(Graphics2D g2, int[] xVals, double[] yVals, int margem, int largura, int altura, double maxY) {
        for (int i = 0; i < xVals.length - 1; i++) {
            int x1 = margem + (int) ((double) i / (xVals.length - 1) * largura);
            int y1 = getHeight() - margem - (int) ((yVals[i] / maxY) * altura);
            int x2 = margem + (int) ((double) (i + 1) / (xVals.length - 1) * largura);
            int y2 = getHeight() - margem - (int) ((yVals[i + 1] / maxY) * altura);
            g2.drawLine(x1, y1, x2, y2);
            g2.fillOval(x1 - 3, y1 - 3, 6, 6);
        }
    }

    private double max(double[] arr) {
        double max = arr[0];
        for (double v : arr) if (v > max) max = v;
        return max;
    }

    public static void exibirGrafico(String algoritmo, int[] tamanhos, double[] temposSerial, double[] temposParalelo) {
        JFrame frame = new JFrame("Gráfico de Desempenho - " + algoritmo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GeradorGrafico(algoritmo, tamanhos, temposSerial, temposParalelo));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Novo método: salva como PNG
    public static void salvarGrafico(String algoritmo, int[] tamanhos, double[] temposSerial, double[] temposParalelo, String nomeArquivo) {
        GeradorGrafico panel = new GeradorGrafico(algoritmo, tamanhos, temposSerial, temposParalelo);
        panel.setSize(900, 600);
        BufferedImage imagem = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagem.createGraphics();
        panel.paint(g2d);
        g2d.dispose();
        try {
            ImageIO.write(imagem, "png", new File(nomeArquivo));
            System.out.println("Gráfico salvo: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar gráfico: " + e.getMessage());
        }
    }
}
