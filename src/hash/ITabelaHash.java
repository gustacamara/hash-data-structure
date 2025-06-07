package hash;

public interface ITabelaHash {
    void inserir(int valor);
    boolean buscar(int valor);
    boolean temColisao(int valor);
    int quantidadeColisao();
    int getComparacoesBusca(int valor);
    String getNome();
}