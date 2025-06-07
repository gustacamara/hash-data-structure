package MultiplicacaoHash;

public class MultiplicacaoHash {
    public Node[] tabela;
    public int tamanho;
    public int colisoes = 0;
    private static final double constante = 0.6180339887;

    public MultiplicacaoHash(int tamanho) {
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

        if (tabela[indice] != null) {
            colisoes++;
        }

        Node novo = new Node(elemento);
        novo.setProximo(tabela[indice]);
        tabela[indice] = novo;

    }

    public boolean removeElemento(int valor) {
        int indice = funcaoHash(valor);
        Node atual = tabela[indice];
        Node anterior = null;


        while (atual != null) {
            if (atual.getValor() == valor) {
                if (anterior == null) {
                    tabela[indice] = atual.getProximo();
                } else {
                    anterior.setProximo(atual.getProximo());
                }
                return true;
            }
            anterior = atual;
            atual = atual.getProximo();
        }

        return false;
    }

    public boolean buscar(int valor) {
        int indice = funcaoHash(valor);
        Node atual = tabela[indice];
        int comparacoes = 0;

        while (atual != null) {
            comparacoes++;
            if (atual.getValor() == valor) {
                exibirComparacoes(valor, comparacoes);
                return true;
            }

            atual = atual.getProximo();
        }
        return  false;
        }

    public void exibirColisoes() {
        System.out.println("Total de colisões: " + colisoes);
    }

    public void exibirComparacoes(int valor,int comparacoes){
        System.out.println("Valor: " + valor);
        System.out.println("Comparações: " + comparacoes);
    }
}


