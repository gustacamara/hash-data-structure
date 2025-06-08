package hash.hashmultiplicacao;
import hash.ITabelaHash;
import hash.No;

public class TabelaHashMultiplicacao implements ITabelaHash{
    public No[] tabela;
    public int tamanho;
    public int quantidadeColisoes = 0;
    private String nome = "Multiplicação";
    private static final double constante = 0.6180339887;


    public TabelaHashMultiplicacao(int tamanho) {
        this.tabela = new No[tamanho];
        this.tamanho = tamanho;
    }


    private int funcaoHash(int valor) {
        double produto = valor * constante;
        double fracao = produto - (int) produto;
        return (int)(tamanho * fracao);
    }


    @Override
    public void inserir(int valor) {
        int indice = funcaoHash(valor);

        if (temColisao(valor)) {
            quantidadeColisoes++;
        }

        No novo = new No(valor);
        novo.setProximo(tabela[indice]);
        tabela[indice] = novo;
    }


    public boolean removerElemento(int valor) {
        int indice = funcaoHash(valor);
        No atual = tabela[indice];
        No anterior = null;


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


    @Override
    public boolean buscar(int valor) {
        int indice = funcaoHash(valor);
        No atual = tabela[indice];

        while (atual != null) {
            if (atual.getValor() == valor) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }


    @Override
    public int getComparacoesBusca(int valor) {
        int comparacoes = 0;

        int indice = funcaoHash(valor);
        No atual = tabela[indice];

        while (atual != null) {
            comparacoes++;
            if (atual.getValor() == valor) {
                break;
            }
            atual = atual.getProximo();
        }

        return comparacoes;
    }

    
    @Override
    public boolean temColisao(int valor) {
        int indice = funcaoHash(valor);
        return tabela[indice] != null;
    }

    
    @Override
    public String getNome() {
        return nome;
    }


    @Override
    public int quantidadeColisao() {
        return this.quantidadeColisoes;
    }


   public void exibirTabela(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            System.out.print("[" + i + "]: ");
            No atual = tabela[i];
            while (atual != null) {
                System.out.print(atual.getValor() + " -> ");
                atual = atual.getProximo();
            }
            System.out.println("null");
        }
    }
}


