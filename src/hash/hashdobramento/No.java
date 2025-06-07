package hashdobramento;
import hashgenerico.Registro;

public class No {
    Registro valor;
    No proximo;

    public No(Registro valor) {
        this.valor = valor;
        this.proximo = null;
    }
}
