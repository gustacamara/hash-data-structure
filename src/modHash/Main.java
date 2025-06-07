package modHash;

import java.util.Random;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        TabelaHashMod tabela = new TabelaHashMod(1000);
        Random rand = new Random();
        long inicio = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            tabela.inseri(rand.nextInt(1_000_000, 2_000_000));
        }
        long fim =  (System.currentTimeMillis() - inicio);
        System.out.println(fim);
    }
}
