package hashgenerico;

public interface ITabelaHash {
    void inserir(Registro registro);
    Registro buscar(int codigo);
    long getColisoes();
    long getComparacoesUltimaBusca();
    String getNome();
}