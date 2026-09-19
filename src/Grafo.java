import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {

    private final boolean dirigido;

    private final Map<String, Vertice> vertices;
    private final Map<Vertice, List<Aresta>> adjacencias;

    public Grafo(boolean dirigido) {
        this.dirigido = dirigido;
        this.vertices = new HashMap<>();
        this.adjacencias = new HashMap<>();
    }

    public void adicionarVertice(Vertice vertice) {
        if (vertices.containsKey(vertice.getRotulo())) {
            throw new IllegalArgumentException(
                    "Já existe um vértice com esse rótulo.");
        }

        vertices.put(vertice.getRotulo(), vertice);
        adjacencias.put(vertice, new ArrayList<>());
    }

    public void adicionarAresta(Vertice origem, Vertice destino) {
        Aresta aresta = new Aresta(origem, destino);

        adjacencias.get(origem).add(aresta);

        if (!dirigido) {
            adjacencias.get(destino)
                    .add(new Aresta(destino, origem));
        }
    }

    public List<Vertice> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    public Vertice getVertice(String rotulo) {
        return vertices.get(rotulo);
    }

    public boolean possuiVertice(String rotulo) {
        return vertices.containsKey(rotulo);
    }

    public List<Aresta> getArestas() {
        List<Aresta> arestas = new ArrayList<>();

        for (List<Aresta> lista : adjacencias.values()) {
            arestas.addAll(lista);
        }

        return arestas;
    }

    public boolean isDirigido() {
        return dirigido;
    }
}