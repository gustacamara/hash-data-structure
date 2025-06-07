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

    public void escreverLinha(String[] valores) {
        try {
            if (escrever != null) {
                escrever.write(String.join(",", valores));
                escrever.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de registro: " + e.getMessage());
        }
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

        public  static void gerarRelatorio(String nomeTabela, int tamanhoVetor, String acao, String tamanhoSeed, long tempoExecucao) {
            String[] colunas = {"Tipo Hash", "Ação", "Tamanho Vetor","Tamanho Seed", "Tempo(ms)"};
            Relatorio relatorio = new Relatorio("resultado.csv", colunas);

            String[] linha = {
                nomeTabela,
                String.valueOf(acao),
                String.valueOf(tamanhoVetor),
                String.valueOf(tamanhoSeed),
                String.valueOf(tempoExecucao)
            };

            relatorio.escreverLinha(linha);
            relatorio.fecharArquivo();
        }
}
