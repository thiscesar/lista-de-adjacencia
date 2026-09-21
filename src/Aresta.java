public class Aresta {
    private static int proximoId = 1;

    private final int id;
    private final Vertice origem;
    private final Vertice destino;
    private final double peso;

    public Aresta(Vertice origem, Vertice destino) {
        this(origem, destino, 1.0);
    }

    public Aresta(Vertice origem, Vertice destino, double peso) {
        this(proximoId++, origem, destino, peso);
    }

    private Aresta(int id, Vertice origem, Vertice destino, double peso) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    Aresta inverter() {
        return new Aresta(id, destino, origem, peso);
    }

    public Vertice getOrigem() {
        return origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public int getId() {
        return id;
    }
}