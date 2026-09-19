import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainCanvas extends JPanel {

    private Grafo grafo;

    private boolean modoAdicionarVertice;
    private boolean modoAdicionarAresta;

    private Vertice origemAresta;

    public MainCanvas(Grafo grafo) {
        this.grafo = grafo;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tratarClique(e);
            }
        });
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
        origemAresta = null;
        repaint();
    }

    public void ativarAdicionarVertice() {
        modoAdicionarVertice = true;
        modoAdicionarAresta = false;
        origemAresta = null;
    }

    public void ativarAdicionarAresta() {
        modoAdicionarAresta = true;
        modoAdicionarVertice = false;
        origemAresta = null;
    }

    private void tratarClique(MouseEvent e) {

        if (modoAdicionarVertice) {
            adicionarVertice(e.getX(), e.getY());
            return;
        }

        if (modoAdicionarAresta) {
            selecionarAresta(e.getX(), e.getY());
        }
    }

    private void adicionarVertice(int x, int y) {

        String rotulo = JOptionPane.showInputDialog(
                this,
                "Rótulo do vértice:");

        if (rotulo == null || rotulo.isBlank()) {
            return;
        }

        rotulo = rotulo.trim();

        if (grafo.possuiVertice(rotulo)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Já existe um vértice com o rótulo " + rotulo + ".");

            return;
        }

        Vertice vertice = new Vertice(rotulo, x, y);

        grafo.adicionarVertice(vertice);

        repaint();
    }

    private void selecionarAresta(int x, int y) {

        Vertice vertice = encontrarVertice(x, y);

        if (vertice == null) {
            return;
        }

        if (origemAresta == null) {
            origemAresta = vertice;
            repaint();
            return;
        }

        Vertice destino = vertice;

        grafo.adicionarAresta(origemAresta, destino);

        origemAresta = null;

        repaint();
    }

    private Vertice encontrarVertice(int x, int y) {

        for (Vertice vertice : grafo.getVertices()) {

            int dx = x - vertice.getX();
            int dy = y - vertice.getY();

            if (dx * dx + dy * dy <= 15 * 15) {
                return vertice;
            }
        }

        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        desenharArestas(g);
        desenharVertices(g);
    }

    private void desenharArestas(Graphics g) {
        for (Aresta aresta : grafo.getArestas()) {

            if (grafo.isDirigido()) {
                desenharSeta(g, aresta);
            } else {
                desenharLinha(g, aresta);
            }
        }
    }

    private void desenharLinha(Graphics g, Aresta aresta) {

        Vertice origem = aresta.getOrigem();
        Vertice destino = aresta.getDestino();

        g.drawLine(
                origem.getX(),
                origem.getY(),
                destino.getX(),
                destino.getY());
    }

    private void desenharSeta(Graphics g, Aresta aresta) {

        Vertice origem = aresta.getOrigem();
        Vertice destino = aresta.getDestino();

        int x1 = origem.getX();
        int y1 = origem.getY();

        int x2 = destino.getX();
        int y2 = destino.getY();

        g.drawLine(x1, y1, x2, y2);

        double angulo = Math.atan2(y2 - y1, x2 - x1);

        int tamanho = 10;

        int x3 = (int) (x2 - tamanho * Math.cos(angulo - Math.PI / 6));
        int y3 = (int) (y2 - tamanho * Math.sin(angulo - Math.PI / 6));

        int x4 = (int) (x2 - tamanho * Math.cos(angulo + Math.PI / 6));
        int y4 = (int) (y2 - tamanho * Math.sin(angulo + Math.PI / 6));

        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x2, y2, x4, y4);
    }

    private void desenharVertices(Graphics g) {

        for (Vertice vertice : grafo.getVertices()) {

            int x = vertice.getX();
            int y = vertice.getY();

            g.fillOval(x - 15, y - 15, 30, 30);

            g.drawString(
                    vertice.getRotulo(),
                    x - 5,
                    y - 20);
        }
    }
}