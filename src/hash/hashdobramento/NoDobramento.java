package hash.hashdobramento;
import hash.Registro;

public class NoDobramento {
    private Registro registro;
    private NoDobramento proximo;

    public NoDobramento(Registro registro) {
        this.registro = registro;
        this.proximo = null;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public NoDobramento getProximo() {
        return proximo;
    }

    public void setProximo(NoDobramento proximo) {
        this.proximo = proximo;
    }
}