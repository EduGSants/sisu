package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;


public class GraficoPizza extends Grafico {

    private List<Double> valores;
    private String[] categorias;

    // -------------------------------------------------
    private final Color[] cores = {
        new Color(31, 119, 180),   // Azul
        new Color(255, 127, 14),   // Laranja
        new Color(44, 160, 44),    // Verde
        new Color(214, 39, 40),    // Vermelho
        new Color(148, 103, 189),  // Roxo
        new Color(140, 86, 75),    // Marrom
        new Color(227, 119, 194),  // Rosa
        new Color(127, 127, 127),  // Cinza
        new Color(188, 189, 34),   // Oliva
        new Color(23, 190, 207),   // Ciano
        new Color(174, 199, 232),  // Azul Claro
        new Color(255, 187, 120)   // Laranja Claro
    };
    // -------------------------------------------------
    
    public GraficoPizza() {
        super();
        this.valores = new ArrayList<>();
        this.categorias = new String[0];
    }
    // -------------------------------------------------

    public void atualizarDados(String[] categorias, List<Double> valores) {
        this.categorias = (categorias != null) ? categorias : new String[0];
        this.valores = (valores != null) ? valores : new ArrayList<>();
        repaint();
    }
    // -------------------------------------------------
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (valores == null || valores.isEmpty() || !temDadosValidos()) {
            g2d.setFont(new Font("Arial", Font.PLAIN, 14));
            g2d.setColor(Color.GRAY);
            g2d.drawString("Sem dados para exibir o gráfico.", 20, 30);
            return;
        }

        int areaGraficoLargura = (int) (getWidth() * 0.60);
        int areaLegendaX = areaGraficoLargura + 10;
        int diametro = Math.min(areaGraficoLargura, getHeight()) - 40;
        int x = (areaGraficoLargura - diametro) / 2;
        int y = (getHeight() - diametro) / 2;

        double total = 100.0;
        double anguloInicial = 90.0;

        // Desenha as fatias da pizza
        for (int i = 0; i < valores.size(); i++) {
            double valorFatia = valores.get(i);
            if (valorFatia <= 0) continue;

            double anguloArco = (valorFatia / total) * 360.0;
            g2d.setColor(cores[i % cores.length]);
            g2d.fillArc(x, y, diametro, diametro, (int) anguloInicial, -(int) Math.round(anguloArco));
            anguloInicial -= anguloArco;
        }

        // Desenha a legenda
        g2d.setFont(new Font("Arial", Font.PLAIN, 11));
        int legendaY = y;
        for (int i = 0; i < categorias.length; i++) {
            if (valores.get(i) > 0) {
                g2d.setColor(cores[i % cores.length]);
                g2d.fillRect(areaLegendaX, legendaY, 12, 12);
                g2d.setColor(Color.BLACK);
                String textoLegenda = String.format("%s: %.2f%%", categorias[i], valores.get(i));
                g2d.drawString(textoLegenda, areaLegendaX + 20, legendaY + 11);
                legendaY += 20;
            }
        }
    }

    private boolean temDadosValidos() {
        if (valores == null) return false;
        for (double val : valores) {
            if (val > 0) return true;
        }
        return false;
    }
}