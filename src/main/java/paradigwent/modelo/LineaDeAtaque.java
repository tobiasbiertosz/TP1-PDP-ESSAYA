package paradigwent.modelo;

import paradigwent.modelo.efectos.EfectoClima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LineaDeAtaque {

    private final TipoLinea tipo;
    private final List<Criatura> criaturas;
    private int multiplicador;

    public LineaDeAtaque(TipoLinea tipo) {
        this.tipo = tipo;
        this.criaturas = new ArrayList<>();
        this.multiplicador = 1;
    }

    public void agregarCriatura(Criatura criatura) {
        criaturas.add(criatura);
    }

    public void duplicarFuerza() {
        multiplicador *= 2;
    }

    public int calcularFuerzaTotal(EfectoClima clima) {
        int total = 0;
        for (Criatura criatura : criaturas) {
            total += criatura.calcularFuerza(clima);
        }
        return total * multiplicador;
    }

    /** Saca la criatura de la linea y la manda al descarte, sin modificadores. */
    public void descartar(Criatura criatura, PilaDescarte descarte) {
        if (criaturas.remove(criatura)) {
            criatura.restablecerFuerza();
            descarte.agregar(criatura);
        }
    }

    public void descartarTodas(PilaDescarte descarte) {
        for (Criatura criatura : new ArrayList<>(criaturas)) {
            descartar(criatura, descarte);
        }
        multiplicador = 1;
    }

    public TipoLinea getTipo() {
        return tipo;
    }

    public List<Criatura> getCriaturas() {
        return Collections.unmodifiableList(criaturas);
    }
}
