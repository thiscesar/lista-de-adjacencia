import java.util.List;

public class ResultadoBusca {

    private final List<Aresta> arvore;
    private final List<Aresta> caminho;
    private final boolean encontrado;

    public ResultadoBusca(List<Aresta> arvore, List<Aresta> caminho, boolean encontrado) {
        this.arvore = arvore;
        this.caminho = caminho;
        this.encontrado = encontrado;
    }

    public List<Aresta> getArvore() {
        return arvore;
    }

    public List<Aresta> getCaminho() {
        return caminho;
    }

    public boolean isEncontrado() {
        return encontrado;
    }
}
