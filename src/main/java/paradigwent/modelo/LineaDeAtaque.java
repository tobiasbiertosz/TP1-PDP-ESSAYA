package paradigwent.modelo;

import java.util.ArrayList;
import java.util.List;


public class LineaDeAtaque {

    private TipoLinea tipo;
    private List<Criatura> criaturas;

    public LineaDeAtaque(TipoLinea tipo) {
        this.tipo = tipo;
        this.criaturas = new ArrayList<>();
    }

    public void agregarCriatura(Criatura criatura) {
        criaturas.add(criatura);
    }

    public int calcularFuerzaTotal() {
        // TODO: sumar la fuerzaAtaque de cada criatura (considerando clima y efectos activos)
        return 0;
    }

    public void limpiar() {
        criaturas.clear();
    }

    public TipoLinea getTipo() {
        return tipo;
    }

    public List<Criatura> getCriaturas() {
        return criaturas;
    }
}
