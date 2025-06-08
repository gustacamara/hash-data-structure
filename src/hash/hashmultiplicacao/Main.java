package hash.hashmultiplicacao;
import hash.LeitorArquivo;
import hash.Relatorio;

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
        long tempo = (fim - inicio);


        System.out.println("Nome tabela: " + nome);
        System.out.println("Tempo de execução: " + tempo + " ms");
        System.out.println("Total de colisões: " + tabela.quantidadeColisao());

        long inicioBusca = System.currentTimeMillis();
        int elemento = 569460123;
        
        tabela.buscar(elemento);

        int comparacoes = tabela.getComparacoesBusca(elemento);
        long fimBusca = System.currentTimeMillis();
        long tempoBusca = (fimBusca - inicioBusca);

        System.out.println("Comparações realizadas: " + comparacoes);
        System.out.println("Tempo de busca: " + tempoBusca + " ms");
        

        // Sobre os paramentros do relatório: Tirando o tempo(long), tamanho(int), e as comparações, o restante dos parametros são do tipo String
        // Relatorio.gerarRelatorioInsercao(nome, "Insercao", tamanho, "1 milhões", tempo); 
        // Relatorio.gerarRelatorioInsercao(nome, "Insercao", tamanho, "5 milhões", tempo);
        Relatorio.gerarRelatorioInsercao(nome, "Insercao", tamanho, "20 milhões", tempo);

        // Relatorio.gerarRelatorioBusca(nome, "Busca", tamanho, "1 milhões", comparacoes, tempoBusca);
        // Relatorio.gerarRelatorioBusca(nome, "Busca", tamanho, "5 milhões", comparacoes, tempoBusca);
        Relatorio.gerarRelatorioBusca(nome, "Busca", tamanho, "20 milhões", comparacoes,tempoBusca);
    }
}