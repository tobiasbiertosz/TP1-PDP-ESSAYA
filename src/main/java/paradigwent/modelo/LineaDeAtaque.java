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
        int total = 0;
        for (Criatura criatura : criaturas) {
            total += criatura.getFuerzaAtaque();
        }
        return total;
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
