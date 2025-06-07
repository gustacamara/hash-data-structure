package MultiplicacaoHash;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        MultiplicacaoHash hash = new MultiplicacaoHash(1000);

        LeitorArquivo leitor = new LeitorArquivo();
        List<Integer> dados = leitor.leitorArquivo("src/20mil.txt");
        System.out.println("Número de elementos lidos: " + dados.size());


        long inicio = System.nanoTime();
        for (int valor : dados) {
            hash.insereElemento(valor);
        }

        long fim = System.nanoTime();

//        hash.exibirTabela();
        hash.exibirColisoes();
        long duracao = fim - inicio;
        System.out.println("\nTempo de execução: " + duracao/1000000 + " seg");


        int[] elementosParaBuscar = {902903776, 543218904, 969486422, 505632081, 156762779};
        for (int elemento : elementosParaBuscar) {

            long inicioBusca = System.nanoTime();
            boolean encontrado = hash.buscar(elemento);
            long fimBusca = System.nanoTime();
            System.out.println("Encontrado? " + encontrado);
            long duracaoBusca = fimBusca - inicioBusca;
            System.out.println("\nTempo de execução: " + duracaoBusca/1000000 + " seg");
        }

    }
}
