package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GraficoLinha extends Grafico {

    private List<Double> notas;
    private final int PADDING = 40;

    public GraficoLinha() {
        super();
        this.notas = new ArrayList<>();
    }
    
    
     //Define os dados e redesenha o componente.
    
    public void setNotas(List<Double> notas) {
        this.notas = (notas != null) ? new ArrayList<>(notas) : new ArrayList<>();
        if (!this.notas.isEmpty()) {
            Collections.sort(this.notas);
        }
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (notas == null || notas.size() < 2) {
            g2d.setFont(new Font("Arial", Font.PLAIN, 14));
            g2d.setColor(Color.GRAY);
            g2d.drawString("Selecione um curso para exibir o gráfico de notas.", 20, 30);
            return;
        }

        // ---------------------------------------------
        double notaMinima = notas.get(0);
        double notaMaxima = notas.get(notas.size() - 1);
        double rangeDeNotas = (notaMaxima - notaMinima) == 0 ? 1 : (notaMaxima - notaMinima);
        int larguraDesenhavel = getWidth() - 2 * PADDING;
        int alturaDesenhavel = getHeight() - 2 * PADDING;

        // ---------------------------------------------
        g2d.setColor(Color.BLACK);
        g2d.drawLine(PADDING, getHeight() - PADDING, PADDING, PADDING); // Eixo Y
        g2d.drawLine(PADDING, getHeight() - PADDING, getWidth() - PADDING, getHeight() - PADDING); // Eixo X

        // ---------------------------------------------
        g2d.setStroke(new BasicStroke(2f));
        g2d.setColor(Color.BLACK); // A linha será preta

        for (int i = 0; i < notas.size() - 1; i++) {// Calcula as coordenadas
            int x1 = PADDING + (int) (i * (double) larguraDesenhavel / (notas.size() - 1));
            int y1 = getHeight() - PADDING - (int) (((notas.get(i) - notaMinima) / rangeDeNotas) * alturaDesenhavel);
            int x2 = PADDING + (int) ((i + 1) * (double) larguraDesenhavel / (notas.size() - 1));
            int y2 = getHeight() - PADDING - (int) (((notas.get(i + 1) - notaMinima) / rangeDeNotas) * alturaDesenhavel);
            
            // Desenha a linha de conexão
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Desenha Nota min e max
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString(String.format("%.2f", notaMinima), PADDING - 35, getHeight() - PADDING + 5);
        g2d.drawString(String.format("%.2f", notaMaxima), PADDING - 35, PADDING);
    }
}