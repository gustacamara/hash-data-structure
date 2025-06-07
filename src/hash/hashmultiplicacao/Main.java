package hash.hashmultiplicacao;
import hash.LeitorArquivo;
import hash.Registro;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        TabelaHashMultiplicacao hash = new TabelaHashMultiplicacao(1000);
        LeitorArquivo leitor = new LeitorArquivo();
        List<Integer> dados = leitor.leitorArquivo("src/data/1mil.txt");


        long inicio = System.currentTimeMillis();
        for (int valor : dados) {
            Registro registro = new Registro(valor);
            hash.inserirElemento(registro);
        }

        long fim = System.currentTimeMillis();
        double segundos = (fim - inicio) / 1_000.0;

        hash.exibirTabela();
        System.out.println("Tempo de execução: " + segundos);

        hash.exibirColisoes();

        

        long inicioBusca = System.currentTimeMillis();
        int elemento = 569460123;

        boolean buscarElemento = hash.buscar(elemento);
        System.out.println(buscarElemento);

        long fimBusca = System.currentTimeMillis();
        long duracaoBusca = fimBusca - inicioBusca;

        System.out.println("Tempo de execução: " + duracaoBusca);

        


    }
}
