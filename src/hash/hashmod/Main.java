package hash.hashmod;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        TabelaHashMod tabela = new TabelaHashMod(1000);
        Random rand = new Random();
        long inicio = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            tabela.inserir(rand.nextInt(1_000_000, 2_000_000));
        }
        int valor = 900_000_000;
        tabela.inserir(valor);
        System.out.println("tem colisoes: " + tabela.temColisao(valor));
        System.out.println("busca: " + tabela.buscar(valor));
        System.out.println("quantidade colisoes: " + tabela.quantidadeColisao());
        System.out.println("quantidade comparacoes: " + tabela.getComparacoesBusca(valor));
        long fim =  (System.currentTimeMillis() - inicio);
        tabela.imprimir(2);
        System.out.println(fim);

    }
}
