package hash;

public class Registro {
    private int codigo;
    private No no;

    public Registro(int codigo) {
        this.codigo = codigo;
        this.no = null;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public No getNo() {
        return no;
    }
    public void setNo(No no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return String.format("%09d", codigo);
    }
}
