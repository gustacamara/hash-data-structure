import hash.ITabelaHash;
import hash.LeitorArquivo;
import hash.Relatorio;
import hash.hashdobramento.TabelaHashDobramento;
import hash.hashmod.TabelaHashMod;
import hash.hashmultiplicacao.TabelaHashMultiplicacao;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LeitorArquivo leitor = new LeitorArquivo();
        List<Integer> dados1kk = leitor.leitorArquivo("src/data/1mil.txt");
        List<Integer> dados5kk = leitor.leitorArquivo("src/data/5mil.txt");
        List<Integer> dados20kk = leitor.leitorArquivo("src/data/20mil.txt");

        ITabelaHash tabela;
        int[] tamanhos = { 1000, 10000, 100000 };
        for (int tamanho : tamanhos) {
            tabela = new TabelaHashMultiplicacao(tamanho);
            roda(dados1kk, tabela, tamanho, "1 milhão");

            tabela = new TabelaHashMultiplicacao(tamanho);
            roda(dados5kk, tabela, tamanho, "5 milhões");

            tabela = new TabelaHashMultiplicacao(tamanho);
            roda(dados20kk, tabela, tamanho, "20 milhões");
        }

        for (int tamanho : tamanhos) {
            tabela = new TabelaHashMod(tamanho);
            roda(dados1kk, tabela, tamanho, "1 milhão");

            tabela = new TabelaHashMod(tamanho);
            roda(dados5kk, tabela, tamanho, "5 milhões");

            tabela = new TabelaHashMod(tamanho);
            roda(dados20kk, tabela, tamanho, "20 milhões");
        }

        for (int tamanho : tamanhos) {
            tabela = new TabelaHashDobramento(tamanho);
            roda(dados1kk, tabela, tamanho, "1 milhão");

            tabela = new TabelaHashDobramento(tamanho);
            roda(dados5kk, tabela, tamanho, "5 milhões");

            tabela = new TabelaHashDobramento(tamanho);
            roda(dados20kk, tabela, tamanho, "20 milhões");
        }
    }

    private static void roda(List<Integer> dados, ITabelaHash tabela, int tamanho, String tamanhoSeed) {
        long inicio = System.currentTimeMillis();
        for (int valor : dados) {
            tabela.inserir(valor);
        }
        long fim = System.currentTimeMillis();
        long tempo = (fim - inicio);

        System.out.println("Nome tabela: " + tabela.getNome());
        System.out.println("Tamanho vetor: " + tamanho);
        System.out.println("Dataset: " + tamanhoSeed);
        System.out.println("Tempo de execução: " + tempo + " ms");
        System.out.println("Total de colisões: " + tabela.quantidadeColisao());

        Relatorio.gerarRelatorioInsercao(tabela.getNome(), "Inserção", tamanho, tamanhoSeed, tempo);

        int[] elementos = { 913246053, 700133575, 801209918, 495618793, 354125406 };
        int comparacoes = 0;
        long inicioBusca = System.currentTimeMillis();
        for (int elemento : elementos) {
            tabela.buscar(elemento);
            comparacoes += tabela.getComparacoesBusca(elemento);
        }
        long fimBusca = System.currentTimeMillis();
        long tempoBusca = (fimBusca - inicioBusca);

        System.out.println("Comparações realizadas: " + comparacoes);
        System.out.println("Tempo de busca: " + tempoBusca + " ms");

        Relatorio.gerarRelatorioBusca(tabela.getNome(), "Busca", tamanho, tamanhoSeed, comparacoes, tempoBusca);
    }
}