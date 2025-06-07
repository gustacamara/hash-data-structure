package hashdobramento;

import hashgenerico.Registro;

public class TabelaHashDobramento {

    private No[] tabela;
    private int tamanho;
    private long colisoes = 0;

    public TabelaHashDobramento(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new No[tamanho];
    }

    public void inserir(Registro registro) {
        int indice = getIndice(registro.getValor());
        No novoNode = new No(registro);

        if (tabela[indice] == null) {
            tabela[indice] = novoNode;
        } else {
            this.colisoes++;
            novoNode.proximo = tabela[indice];
            tabela[indice] = novoNode;
        }
    }

    public Registro buscar(int codigo, ContadorComparacoes contador) {
        int indice = getIndice(codigo);
        No atual = tabela[indice];

        while (atual != null) {
            contador.incrementar();
            if (atual.valor.getValor() == codigo) {
                return atual.valor;
            }
            atual = atual.proximo;
        }

        return null;
    }

    public long getColisoes() {
        return colisoes;
    }

    private int getIndice(int chave) {
        return funcaoHashDobra(chave);
    }

    private int funcaoHashDobra(int chave) {
        String chaveStr = String.format("%09d", chave);
        int soma = 0;
        for (int i = 0; i < 9; i += 3) {
            soma += Integer.parseInt(chaveStr.substring(i, i + 3));
        }
        return soma % this.tamanho;
    }

    public static void main(String[] args) {
        TabelaHashDobramento tabela = new TabelaHashDobramento(101);

        int[] dadosDeTeste = {
                442653457, 729386121, 513485608, 492428383, 397184268, 868887019, 649429136, 179543833, 442470123, 706249339,
                823864522, 171827725, 412536855, 624644865, 335833441, 153322199, 114407336, 179040378, 626593436, 739324528,
                873838367, 731722813, 169996531, 377821312, 126604817, 725330343, 644558837, 755259938, 599184976, 521845339,
                915477839, 480039236, 747372338, 222634375, 516957826, 303799656, 853874984, 804576326, 732296443, 715525549,
                245155604, 734491959, 238383416, 929285031, 281512439, 459634568, 629135432, 638421373, 584941914, 773322524,
                119851684, 731835088, 301911475, 489326442, 641526433, 313271788, 545223235, 712323314, 252199587, 856338861,
                383786523, 723653245, 286542798, 874984263, 679951631, 137456722, 163456208, 574213348, 685333256, 748644837,
                563248384, 843195232, 386532585, 232135436, 862438641, 743433836, 423456836, 321354358, 429683431, 516843468,
                278386438, 643238343, 891816843, 568431684, 884316843, 616843518, 513165843, 213516843, 135168435, 431516843,
                684351684, 351684351, 168435168, 435168435, 168435168, 435168435, 516843516, 843516843, 516843516, 843516843,
                235486134, 995641354, 843543518, 618435135, 135435184, 351843513, 518435135, 843513518, 435135184, 351351843,
                135184351, 351843513, 518435135, 843513518, 435135184, 351351843, 135184351, 351843513, 518435135, 843513518,
                684613518, 168135184, 351841351, 841351843, 518413518, 413518413, 518413518, 413518413, 518413518, 413518413,
                789456123, 123789456, 456123789, 789123456, 123456789, 456789123, 789123456, 123456789, 456789123, 789123456,
                111222333, 222333111, 333111222, 111222333, 222333111, 333111222, 111222333, 222333111, 333111222, 111222333,
                999888777, 888777999, 777999888, 999888777, 888777999, 777999888, 999888777, 888777999, 777999888, 999888777,
                555666777, 666777555, 777555666, 555666777, 666777555, 777555666, 555666777, 666777555, 777555666, 555666777,
                101202303, 202303101, 303101202, 101202303, 202303101, 303101202, 101202303, 202303101, 303101202, 101202303,
                404505606, 505606404, 606404505, 404505606, 505606404, 606404505, 404505606, 505606404, 606404505, 404505606,
                707808909, 808909707, 909707808, 707808909, 808909707, 909707808, 707808909, 808909707, 909707808, 707808909,
                135246758, 246758135, 758135246, 135246758, 246758135, 758135246, 135246758, 246758135, 758135246, 135246758,
                864253197, 253197864, 197864253, 864253197, 253197864, 197864253, 864253197, 253197864, 197864253, 864253197,
                975318642, 318642975, 642975318, 975318642, 318642975, 642975318, 975318642, 318642975, 642975318, 975318642,
                192837465, 837465192, 465192837, 192837465, 837465192, 465192837, 192837465, 837465192, 465192837, 192837465,
                564738291, 738291564, 291564738, 564738291, 738291564, 291564738, 564738291, 738291564, 291564738, 564738291
        };

        long tempoInicial = System.nanoTime();

        int tamanhoDoArrayDeTeste = 250;
        for (int i = 0; i < tamanhoDoArrayDeTeste; i++) {
            tabela.inserir(new Registro(dadosDeTeste[i]));
        }

        long tempoFinal = System.nanoTime();

        long duracaoEmNanossegundos = tempoFinal - tempoInicial;
        double duracaoEmMilissegundos = duracaoEmNanossegundos / 1_000_000.0;

        System.out.println("Teste com array de " + tamanhoDoArrayDeTeste + " itens concluído.");
        System.out.println("Tamanho da Tabela: " + tabela.tamanho);
        System.out.println("Total de Colisões: " + tabela.getColisoes());
        System.out.printf("Tempo de Execução (Inserção): %.4f ms\n", duracaoEmMilissegundos);
    }
}