package hash;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LeitorArquivo {
    public List<Integer> leitorArquivo(String caminhoArquivo) {
        List<Integer> dados = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    dados.add(Integer.parseInt(linha.trim()));
                }
            }
        } catch (Exception erro) {
            System.out.println("Houve um erro ao ler o arquivo!!!");
        }

        return dados;
    }
}
