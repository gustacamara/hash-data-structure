package hash.hashdobramento;

import hash.ITabelaHash;
import hash.Registro;

public class TabelaHashDobramento implements ITabelaHash {
    @Override
    public String getNome() {
        return "HashFolding";
    }
    private final NoDobramento[] tabela;
    private final int capacidade;
    private int quantidadeColisoes = 0;

    public TabelaHashDobramento(int capacidade) {
        this.capacidade = capacidade;
        this.tabela = new NoDobramento[capacidade];
    }

    private int funcaoHash(int codigo) {
        String chaveStr = String.format("%09d", codigo);
        int soma = 0;
        for (int i = 0; i < chaveStr.length(); i += 3) {
            soma += Integer.parseInt(chaveStr.substring(i, i + 3));
        }
        return soma % this.capacidade;
    }

    @Override
    public void inserir(int valor) {
        Registro novoRegistro = new Registro(valor);
        NoDobramento novoNo = new NoDobramento(novoRegistro);
        int indice = funcaoHash(valor);

        if (tabela[indice] == null) {
            tabela[indice] = novoNo;
        } else {
            this.quantidadeColisoes++;

            NoDobramento atual = tabela[indice];
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novoNo);
        }
    }

    @Override
    public boolean buscar(int valor) {
        int indice = funcaoHash(valor);
        NoDobramento atual = tabela[indice];

        while (atual != null) {
            if (atual.getRegistro().getCodigo() == valor) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    @Override
    public boolean temColisao(int valor) {
        int indice = funcaoHash(valor);
        return tabela[indice] != null && tabela[indice].getProximo() != null;
    }

    @Override
    public int quantidadeColisao() {
        return this.quantidadeColisoes;
    }

    @Override
    public int getComparacoesBusca(int valor) {
        int indice = funcaoHash(valor);
        NoDobramento atual = tabela[indice];
        int comparacoes = 0;

        while (atual != null) {
            comparacoes++;
            if (atual.getRegistro().getCodigo() == valor) {
                break;
            }
            atual = atual.getProximo();
        }

        if (comparacoes == 0) {
            return 1;
        }

        return comparacoes;
    }
}