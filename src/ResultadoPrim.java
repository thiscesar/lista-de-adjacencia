import java.util.List;

public class ResultadoPrim {

    private final List<Aresta> arestas;
    private final double custoTotal;

    public ResultadoPrim(List<Aresta> arestas, double custoTotal) {
        this.arestas = arestas;
        this.custoTotal = custoTotal;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public double getCustoTotal() {
        return custoTotal;
    }
}
