package hashgenerico;

public class Registro {

    private int codigo;

    public Registro(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return String.format("%09d", codigo);
    }
}
