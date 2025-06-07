package hash.hashmultiplicacao;
import hash.LeitorArquivo;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int tamanho = 1000;
        TabelaHashMultiplicacao hash = new TabelaHashMultiplicacao(tamanho);

        LeitorArquivo leitor = new LeitorArquivo();
        List<Integer> dados = leitor.leitorArquivo("src/data/20mil.txt");

        long inicio = System.currentTimeMillis();
        for (int valor : dados) {
            hash.inserir(valor);
        }

        long fim = System.currentTimeMillis();


        // hash.exibirTabela(tamanho);
        System.out.println("Tempo de execução: " + (fim - inicio) + " ms");
        System.out.println("Total de colisões: " + hash.quantidadeColisao());

        long inicioBusca = System.currentTimeMillis();
        int elemento = 569460123;
        
        hash.buscar(elemento);

        int comparacoes = hash.getComparacoesBusca(elemento);
        long fimBusca = System.currentTimeMillis();

        System.out.println("Comparações realizadas: " + comparacoes);
        System.out.println("Tempo de busca: " + (fimBusca - inicioBusca) + " ms");

    }
}