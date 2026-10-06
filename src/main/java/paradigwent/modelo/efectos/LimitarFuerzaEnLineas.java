package paradigwent.modelo.efectos;

import paradigwent.modelo.TipoLinea;

import java.util.EnumSet;
import java.util.Set;

/** Ej: "Escarcha": la fuerza de las criaturas de ciertas lineas no pasa de 1. */
public class LimitarFuerzaEnLineas implements EfectoClima {

    private final int fuerzaMaxima;
    private final Set<TipoLinea> lineasAfectadas;

    public LimitarFuerzaEnLineas(int fuerzaMaxima, Set<TipoLinea> lineasAfectadas) {
        this.fuerzaMaxima = fuerzaMaxima;
        this.lineasAfectadas = EnumSet.noneOf(TipoLinea.class);
        this.lineasAfectadas.addAll(lineasAfectadas);
    }

    @Override
    public int modificar(TipoLinea linea, int fuerza) {
        return lineasAfectadas.contains(linea) ? Math.min(fuerza, fuerzaMaxima) : fuerza;
    }
}
