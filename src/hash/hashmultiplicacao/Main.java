package hash.hashmultiplicacao;
import hash.LeitorArquivo;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int tamanho = 1000;
        TabelaHashMultiplicacao tabela = new TabelaHashMultiplicacao(tamanho);
        String nome = tabela.getNome();

        LeitorArquivo leitor = new LeitorArquivo();
        List<Integer> dados = leitor.leitorArquivo("src/data/20mil.txt");

        long inicio = System.currentTimeMillis();
        for (int valor : dados) {
            tabela.inserir(valor);
        }

        long fim = System.currentTimeMillis();


        // hash.exibirTabela(tamanho);
        System.out.println("Nome tabela: " + nome);
        System.out.println("Tempo de execução: " + (fim - inicio) + " ms");
        System.out.println("Total de colisões: " + tabela.quantidadeColisao());

        long inicioBusca = System.currentTimeMillis();
        int elemento = 569460123;
        
        tabela.buscar(elemento);

        int comparacoes = tabela.getComparacoesBusca(elemento);
        long fimBusca = System.currentTimeMillis();

        System.out.println("Comparações realizadas: " + comparacoes);
        System.out.println("Tempo de busca: " + (fimBusca - inicioBusca) + " ms");

    }
}