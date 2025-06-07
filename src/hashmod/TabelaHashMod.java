package hashmod;

public class TabelaHashMod {
    No[] tabela;
    int tamanho;

    public TabelaHashMod(int tamanho) {
        this.tabela = new No[tamanho];
        this.tamanho = tamanho;
    }

    public void inserir(int valor) {
        int indice = getLinhaChave(valor);
        if (tabela[indice] == null) {
            tabela[indice] = new No(valor);
        }
        else {
            tabela[indice].setProximo(inserir(tabela[indice].getProximo(), valor));
        }
    }

    public No inserir(No no, int valor) {
        No novoNo = new No(valor);
        if (no == null || valor < no.getValor()) {
            novoNo.setProximo(no);
            return novoNo;
        }
        No atual = no;
        while (atual.getProximo() != null && atual.getProximo().getValor() < valor) {
            atual = atual.getProximo();
        }
        if (atual.getProximo() == null) {
            atual.setProximo(novoNo);
            return no;
        }
        novoNo.setProximo(atual.getProximo());
        atual.setProximo(novoNo);
        return no;
    }

    public void remove(int valor) {
        No noChave = tabela[getLinhaChave(valor)];
        remove(noChave.getProximo(), valor);
    }

    public void remove(No no, int valor) {
        No anterior = no;
        No atual = no;

        while (atual.getValor() != valor || atual.getProximo() != null) {
            anterior = atual;
            atual = atual.getProximo();
        }
        if (atual.getValor() != valor) {
            return;
        }
        anterior.setProximo(atual.getProximo());
    }

    public boolean buscar(int valor) {
        No noChave = tabela[getLinhaChave(valor)];
        if (noChave.getValor() == valor) {
            return true;
        }
        No atual = noChave.getProximo();
        while (atual.getValor() != valor || atual.getProximo() != null) {
            atual = atual.getProximo();
        }
        return atual.getValor() == valor;
    }

    public void imprimir(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            System.out.print("Linha " + i + ": ");
            No current = tabela[i];
            while (current != null) {
                System.out.print(current.getValor() + " -> ");
                current = current.getProximo();
            }
            System.out.println("null");
        }
    }

    public int getLinhaChave(int valor) {
        return valor % tamanho;
    }
}