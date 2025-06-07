package hash.hashmod;

import hash.ITabelaHash;
import hash.No;
import hash.Registro;

public class TabelaHashMod implements ITabelaHash {
    private Registro[] tabela;
    private int tamanho;
    private String nome = "Mod";

    public TabelaHashMod(int tamanho) {
        this.tabela = new Registro[tamanho];
        this.tamanho = tamanho;
    }

    public void inserir(int valor) {
        int indice = getLinhaChave(valor);
        if (tabela[indice] == null) {
            tabela[indice].setCodigo(valor);
        }
        else {
            tabela[indice].setNo(inserir(tabela[indice].getNo(), valor));
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
        No noChave = tabela[getLinhaChave(valor)].getNo();
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
            System.out.print("Linha " + i + " ");
            No current = tabela[i].getNo();
            System.out.println("[" + tabela[i].getCodigo() + "] : ");
            while (current != null) {
                System.out.print(current.getValor() + " -> ");
                current = current.getProximo();
            }
            System.out.println("null");
        }
    }

    public boolean temColisao(int valor){
        return true;
    }
    public int quantidadeColisao(){
        return 4;
    };
    public int getComparacoesBusca(int valor) {
        return 5;
    }

    public int getLinhaChave(int valor) {
        return valor % tamanho;
    }

    public Registro[] getTabela() {
        return tabela;
    }

    public void setTabela(Registro[] tabela) {
        this.tabela = tabela;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}