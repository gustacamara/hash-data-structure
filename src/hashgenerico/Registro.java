package hashgenerico;

import java.util.Objects;

public class Registro {
    private final int codigo;

    public Registro(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registro other = (Registro) o;
        return this.codigo == other.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codigo);
    }
}