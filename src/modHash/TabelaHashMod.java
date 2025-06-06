package modHash;

public class TabelaHashMod {
    Node[] tabela;
    int tamanho;

    public TabelaHashMod(int tamanho) {
        this.tabela = new Node[tamanho];
        this.tamanho = tamanho;
    }

    public void inseri(int valor) {
        int indice = getLinhaChave(valor);
        Node noChave = tabela[indice];
        if (noChave == null) {
            tabela[indice] = new Node(valor);
        } else {
            inseri(noChave.getProximo(), valor);
        }
    }

    public void inseri(Node no, int valor) {
        if (no == null) {
            no = new Node(valor);
            return;
        }
        Node anterior = no;
        Node atual = no;

        while (atual.getValor() < valor || atual.getProximo() != null) {
            anterior = atual;
            atual = atual.getProximo();
        }

        if (atual.getValor() == valor) {
            anterior = atual;
            atual.setProximo(new Node(valor));
            atual.getProximo().setProximo(anterior.getProximo());
            return;
        }
        if (atual.getValor() > valor) {
            Node noInserir = new Node(valor);
            anterior.setProximo(noInserir);
            noInserir.setProximo(atual);
            return;
        }

        inseri(atual, valor);
    }

    public void remove(int valor) {
        Node noChave = tabela[getLinhaChave(valor)];
        remove(noChave.getProximo(), valor);
    }

    public void remove(Node node, int valor) {
        Node anterior = node;
        Node atual = node;

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
        Node nodeChave = tabela[getLinhaChave(valor)];
        if (nodeChave.getValor() == valor) {
            return true;
        }
        Node atual = nodeChave.getProximo();
        while (atual.getValor() != valor || atual.getProximo() != null) {
            atual = atual.getProximo();
        }
        return atual.getValor() == valor;
    }

    public int getLinhaChave(int valor) {
        return valor % tamanho;
    }
}