import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        Grafo grafo = criarGrafo();

        MainCanvas canvas = new MainCanvas(grafo);

        JFrame frame = new JFrame("Editor de Grafos");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        frame.setJMenuBar(criarMenu(frame, canvas));

        frame.setContentPane(canvas);
        frame.setVisible(true);
    }

    private static Grafo criarGrafo() {

        int resposta = JOptionPane.showConfirmDialog(
                null,
                "O grafo é dirigido?",
                "Novo grafo",
                JOptionPane.YES_NO_OPTION);

        return new Grafo(resposta == JOptionPane.YES_OPTION);
    }

    private static JMenuBar criarMenu(
            JFrame frame,
            MainCanvas canvas) {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuGrafo = new JMenu("Grafo");

        JMenuItem novo = new JMenuItem("Novo");
        JMenuItem adicionarVertice = new JMenuItem("Adicionar vértice");
        JMenuItem adicionarAresta = new JMenuItem("Adicionar aresta");

        novo.addActionListener(e -> {

            Grafo novoGrafo = criarGrafo();

            canvas.setGrafo(novoGrafo);
        });

        adicionarVertice.addActionListener(e -> {
            canvas.ativarAdicionarVertice();
        });

        adicionarAresta.addActionListener(e -> {
            canvas.ativarAdicionarAresta();
        });

        menuGrafo.add(novo);
        menuGrafo.addSeparator();
        menuGrafo.add(adicionarVertice);
        menuGrafo.add(adicionarAresta);

        menuBar.add(menuGrafo);

        return menuBar;
    }
}