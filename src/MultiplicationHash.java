public class MultiplicationHash {

    public class Node {
        private int valor;
        private Node proximo;

        public Node(int valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    public Node[] tabela;
    public int tamanho;
    private static final double constante = 0.6180339887;

    public MultiplicationHash(int tamanho) {
        this.tabela = new Node[tamanho];
        this.tamanho = tamanho;
    }

    private int funcaoHash(int valor) {
        double produto = valor * constante;
        double fracao = produto - (int) produto;
        return (int)(tamanho * fracao);
    }

    public void insereElemento(int elemento) {
        int indice = funcaoHash(elemento);
        Node novo = new Node(elemento);
        novo.proximo = tabela[indice];
        tabela[indice] = novo;
    }

    public boolean removeElemento(int valor) {
        int indice = funcaoHash(valor);
        Node atual = tabela[indice];
        Node anterior = null;

        while (atual != null) {
            if (atual.valor == valor) {
                if (anterior == null) {
                    tabela[indice] = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        }

        return false;
    }


    public boolean buscar(int valor) {
        int indice = funcaoHash(valor);
        Node atual = tabela[indice];

        while (atual != null) {
            if (atual.valor == valor) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }


    public void exibirTabela() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print("[" + i + "]: ");
            Node atual = tabela[i];
            while (atual != null) {
                System.out.print(atual.valor + " -> ");
                atual = atual.proximo;
            }
            System.out.println("null");
        }
    }
}


