package hash.hashmod;

import hash.ITabelaHash;
import hash.No;
import hash.Registro;

public class TabelaHashMod implements ITabelaHash {
    private Registro[] tabela;
    private int tamanho;
    private String nome = "HashMod";

    public TabelaHashMod(int tamanho) {
        this.tabela = new Registro[tamanho];
        this.tamanho = tamanho;
    }

    public void inserir(int valor) {
        int indice = getLinhaChave(valor);
        if (tabela[indice] == null) {
            tabela[indice] = new Registro(valor);
        }
        else {
            tabela[indice].setNo(inserir(tabela[indice].getNo(), valor));
        }
    }

    public No inserir(No no, int valor) {
        No novoNo = new No(valor);
        if (no == null) {
            return novoNo;
        }
        No atual = no;
        while (atual.getProximo() != null && atual.getValor() < valor) {
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
        Registro reg = tabela[getLinhaChave(valor)];
        if (reg.getCodigo() == valor) {
            int novoCodigo = reg.getNo().getValor();
            reg.getNo().setProximo(reg.getNo());
            reg.setCodigo(novoCodigo);
        } else {
            remove(reg.getNo(), valor);

        }
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
        No atual = tabela[getLinhaChave(valor)].getNo();
        if (atual == null) {
            return false;
        }
        while (atual.getValor() != valor && atual.getProximo() != null) {
            atual = atual.getProximo();
        }
        return atual.getValor() == valor;
    }

    public void imprimir(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            System.out.print("Linha " + i + " ");
            No current = tabela[i].getNo();
            System.out.print("[" + tabela[i].getCodigo() + "] : ");
            while (current != null) {
                System.out.print(current.getValor() + " -> ");
                current = current.getProximo();
            }
            System.out.println("null");
        }
    }

    public boolean temColisao(int valor){
        if (tabela[getLinhaChave(valor)] != null) {
            return true;
        }
        return false;
    }
    public int quantidadeColisao(){
        int colisoesTabela = 0;
        for(Registro i: tabela) {
            if (i != null) {
                colisoesTabela ++;
            }
        }

        return colisoesTabela;
    };
    public int getComparacoesBusca(int valor) {
        int comparacoes = 1;
        Registro reg = tabela[getLinhaChave(valor)];
        if (reg == null) {
            return 0;
        }
        if (reg.getCodigo() == valor) {
            return comparacoes;
        }
        No atual = reg.getNo();
        comparacoes ++;
        if (atual == null) {
            return comparacoes;
        }
        while (atual.getValor() != valor && atual.getProximo() != null) {
            atual = atual.getProximo();
            comparacoes ++;
        }
        return comparacoes;
    }

    public int getLinhaChave(int valor) {
        return valor % tamanho;
    }

    public Registro[] getTabela() {
        return tabela;
    }

    public int getTamanho() {
        return tamanho;
    }

    @Override
    public String getNome() {
        return nome;
    }

}