package hashgenerico;

public class No {
    public Registro valor;
    public No proximo;

    public No(Registro valor) {
        this.valor = valor;
        this.proximo = null;
    }
}