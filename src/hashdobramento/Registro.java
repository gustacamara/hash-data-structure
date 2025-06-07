package hashdobramento;

public class Registro {
    private int valor;

    public Registro(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return String.format("%09d", valor);
    }
}