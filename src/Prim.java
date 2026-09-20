import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prim {

    public ResultadoPrim calculaPrim(Grafo grafo, Vertice origem) {

        if (grafo.isDirigido()) {
            throw new IllegalArgumentException(
                    "Prim não se aplica, para grafos dirigidos");
        }

        Set<Vertice> naArvore = new HashSet<>();
        List<Aresta> arestasAGM = new ArrayList<>();
        double custoTotal = 0;

        naArvore.add(origem); // vai registar best vertices

        while (naArvore.size() < grafo.getVertices().size()) {

            Aresta menorAresta = null;

            for (Aresta aresta : grafo.getArestas()) {

                boolean dentroOrigem = naArvore.contains(aresta.getOrigem());
                boolean dentroDestino = naArvore.contains(aresta.getDestino());

                if (dentroOrigem == dentroDestino) {
                    continue;
                }

                if (menorAresta == null || aresta.getPeso() < menorAresta.getPeso()) {
                    menorAresta = aresta;
                }
            }

            if (menorAresta == null) {
                break;
            }

            Vertice novoVertice = naArvore.contains(menorAresta.getOrigem())
                    ? menorAresta.getDestino()
                    : menorAresta.getOrigem();

            naArvore.add(novoVertice);
            arestasAGM.add(menorAresta);
            custoTotal += menorAresta.getPeso();
        }

        return new ResultadoPrim(arestasAGM, custoTotal);
    }
}
