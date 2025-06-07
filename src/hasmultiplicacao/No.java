package hasmultiplicacao;

import hashgenerico.Registro;

public class No {
    private Registro registro;
    private No proximo;

    public No(Registro registro) {
        this.registro = registro;
        this.proximo = null;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}

