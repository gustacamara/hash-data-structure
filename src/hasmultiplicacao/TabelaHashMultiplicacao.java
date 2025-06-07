package hasmultiplicacao;

import hashgenerico.Registro;

public class TabelaHashMultiplicacao {
    public No[] tabela;
    public int tamanho;
    public int colisoes = 0;
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

    public void inserirElemento(Registro registro) {
        int indice = funcaoHash(registro.getCodigo());

        if (tabela[indice] != null) {
            colisoes++;
        }

        No novo = new No(registro);
        novo.setProximo(tabela[indice]);
        tabela[indice] = novo;

    }

    public boolean removerElemento(int valor) {
        int indice = funcaoHash(valor);
        No atual = tabela[indice];
        No anterior = null;


        while (atual != null) {
            Registro reg = atual.getRegistro();
            if (reg != null && reg.getCodigo() == valor) {
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
        No atual = tabela[indice];
        int comparacoes = 0;

        while (atual != null) {
            comparacoes++;
            if (atual.getRegistro().getCodigo() == valor) {
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
        System.out.println("Total de Comparações: " + comparacoes);
    }


   public void exibirTabela() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print("[" + i + "]: ");
            No atual = tabela[i];
            while (atual != null) {
                System.out.print(atual.getRegistro().getCodigo() + " -> ");
                atual = atual.getProximo();
            }
            System.out.println("null");
        }
    }

}


