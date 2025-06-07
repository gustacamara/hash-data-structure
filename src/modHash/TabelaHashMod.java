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
        if (tabela[indice] == null) {
            tabela[indice] = new Node(valor);
        }
        else {
            tabela[indice].setProximo(inseri(tabela[indice].getProximo(), valor));
        }
    }

    public Node inseri(Node node, int valor) {
        Node novoNode = new Node(valor);
        if (node == null || valor < node.getValor()) {
            novoNode.setProximo(node);
            return novoNode;
        }
        Node atual = node;
        while (atual.getProximo() != null && atual.getProximo().getValor() < valor) {
            atual = atual.getProximo();
        }
        if (atual.getProximo() == null) {
            atual.setProximo(novoNode);
            return node;
        }
        novoNode.setProximo(atual.getProximo());
        atual.setProximo(novoNode);
        return node;
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

    public void imprime(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            System.out.print("Linha " + i + ": ");
            Node current = tabela[i];
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