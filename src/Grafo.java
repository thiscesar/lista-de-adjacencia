import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {

    private final boolean dirigido;

    private final Map<String, Vertice> vertices;
    private final Map<Vertice, List<Aresta>> adjacencias;

    public Boolean validaVertice(Vertice vertice, boolean remove) { 
        // ação false = adiciona | true = remove
        boolean rotulo = vertices.containsKey(vertice.getRotulo());

        if (rotulo && !remove) {
            throw new IllegalArgumentException(
                    "Já existe um vértice com esse rótulo.");

        } else if (!rotulo && remove) {
            throw new IllegalArgumentException(
                    "Escolha um vértice existente");
        }

        return true;
    }

    public Grafo(boolean dirigido) {
        this.dirigido = dirigido;
        this.vertices = new HashMap<>();
        this.adjacencias = new HashMap<>();
    }

    public void adicionarVertice(Vertice vertice) {
        validaVertice(vertice, false);
        vertices.put(vertice.getRotulo(), vertice);
        adjacencias.put(vertice, new ArrayList<>());
    }

    public void removerVertices(Vertice vertice) {
        validaVertice(vertice, true);
        
        vertices.remove(vertice.getRotulo());
        adjacencias.remove(vertice);

        for (List<Aresta> lista : adjacencias.values()) {
            lista.removeIf(aresta -> aresta.getDestino().equals(vertice));
        }

    }

    public void removerAresta(Aresta aresta) {
        List<Aresta> listaOrigem = adjacencias.get(aresta.getOrigem());
        boolean removeu = listaOrigem.remove(aresta);

        if (!removeu) {
            throw new IllegalArgumentException(
                    "A aresta informada não existe no grafo.");
        }

        if (!dirigido) {
            List<Aresta> listaDestino = adjacencias.get(aresta.getDestino());
            listaDestino.removeIf(a -> a.getId() == aresta.getId());
        }
    }

    public void adicionarAresta(Vertice origem, Vertice destino, double peso) {
        Aresta aresta = new Aresta(origem, destino, peso);

        adjacencias.get(origem).add(aresta);

        if (!dirigido) {
            adjacencias.get(destino).add(aresta.inverter());
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

    public List<Aresta> getArestasDe(Vertice vertice) {
        return new ArrayList<>(adjacencias.get(vertice));
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