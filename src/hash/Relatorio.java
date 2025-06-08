package hash;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Relatorio {
    private transient BufferedWriter escrever;

    public Relatorio(String nomeArquivo, String[] colunas) {
        try{
            Path caminho = Paths.get(nomeArquivo);
            boolean novoArquivo = Files.notExists(caminho);

            escrever = new BufferedWriter(new FileWriter(nomeArquivo, true)); 

          if (novoArquivo && colunas != null) {
                escrever.write(String.join(",", colunas));
                escrever.newLine();
            }
        } catch(IOException e) {
            System.out.println("Erro ao criar ou abrir o arquivo de registro: " + e.getMessage());
        }
    }


    public void escreverLinhaInsercao(String[] valores, long tempoInsercao) {
        try {
            if (escrever != null) {
                String linha = String.join(",", valores) + "," + tempoInsercao;
                escrever.write(linha);
                escrever.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de registro: " + e.getMessage());
        }
    }


    public void escreverLinhaBusca(String[] valores, int comparacoes, long tempoBusca) {
        try {
            if (escrever != null) {
                String linha = String.join(",", valores) + "," + comparacoes + "," + tempoBusca;
                escrever.write(linha);
                escrever.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de registro: " + e.getMessage());
        }
    }


    public static void gerarRelatorioInsercao(String nomeTabela, String acao, int tamanhoVetor, String tamanhoSeed, long tempoExecucao) {
        String[] colunas = {"Tipo Hash", "Ação", "Tamanho Vetor", "Tamanho Seed", "Tempo(ms)"};
        Relatorio relatorio = new Relatorio("relatorioInsercao.csv", colunas);
        String vetor = Integer.toString(tamanhoVetor);

        String[] linha = {
            nomeTabela,
            acao,
            vetor,
            tamanhoSeed
        };

        relatorio.escreverLinhaInsercao(linha, tempoExecucao);
        relatorio.fecharArquivo();
    }


     public static void gerarRelatorioBusca(String nomeTabela, String acao, int tamanhoVetor, String tamanhoSeed, int comparacoes, long tempoExecucao) {
        String[] colunas = {"Tipo Hash", "Ação", "Tamanho Vetor", "Tamanho Seed", "Número de Comparações", "Tempo(ms)"};
        Relatorio relatorio = new Relatorio("relatorioBusca.csv", colunas);
        String vetor = Integer.toString(tamanhoVetor);

        String[] linha = {
            nomeTabela,
            acao,
            vetor,
            tamanhoSeed
        };

        relatorio.escreverLinhaBusca(linha, comparacoes, tempoExecucao);
        relatorio.fecharArquivo();
    }


    public void fecharArquivo() {
        try {
            if (escrever != null) {
                escrever.flush();
                escrever.close();
            }

        } catch (IOException e) {
            System.out.println("Erro ao fechar o arquivo de registro: " + e.getMessage());
        }
    }
}


