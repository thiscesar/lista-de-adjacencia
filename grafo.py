class Nodo:
    """
    Representa uma célula da lista encadeada de adjacência.
    Equivale ao retângulo do slide: guarda o vizinho e aponta para o próximo nó.
    """
    def __init__(self, vizinho, rotulo_aresta, peso):
        self.vizinho = vizinho              # rótulo do vértice vizinho
        self.rotulo_aresta = rotulo_aresta  # identificador da aresta/arco
        self.peso = peso                    # custo/peso da ligação
        self.prox = None                    # ponteiro para o próximo nó ("/" do slide)

    def __repr__(self):
        return f"[{self.vizinho}, aresta={self.rotulo_aresta}, peso={self.peso}]"

if __name__ == "__main__":
    n1 = Nodo("B", "e1", 5)
    n2 = Nodo("C", "e2", 3)
    n1.prox = n2   # ligando manualmente, só pra ver o ponteiro funcionando

    atual = n1
    while atual is not None:
        print(atual)
        atual = atual.prox