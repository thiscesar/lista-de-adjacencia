import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class BuscaProfundidade {

    public ResultadoBusca buscar(Grafo grafo, Vertice origem, Vertice destino) {

        Stack<Vertice> pilha = new Stack<>();
        Set<Vertice> visitados = new HashSet<>();
        Map<Vertice, Aresta> predecessor = new HashMap<>();

        pilha.push(origem);
        visitados.add(origem);

        boolean encontrado = false;

        while (!pilha.isEmpty() && !encontrado) {

            Vertice atual = pilha.pop();

            if (atual.equals(destino)) {
                encontrado = true;
                break;
            }

            for (Aresta aresta : grafo.getArestasDe(atual)) {

                Vertice vizinho = aresta.getDestino();

                if (!visitados.contains(vizinho)) {
                    visitados.add(vizinho);
                    predecessor.put(vizinho, aresta);
                    pilha.push(vizinho);
                }
            }
        }

        List<Aresta> arvore = new ArrayList<>(predecessor.values());
        List<Aresta> caminho = reconstruirCaminho(predecessor, origem, destino, encontrado);

        return new ResultadoBusca(arvore, caminho, encontrado);
    }

    private List<Aresta> reconstruirCaminho(
            Map<Vertice, Aresta> predecessor,
            Vertice origem,
            Vertice destino,
            boolean encontrado) {

        List<Aresta> caminho = new ArrayList<>();

        if (!encontrado) {
            return caminho;
        }

        Vertice atual = destino;

        while (!atual.equals(origem)) {
            Aresta aresta = predecessor.get(atual);
            caminho.add(0, aresta);
            atual = aresta.getOrigem();
        }

        return caminho;
    }
}
