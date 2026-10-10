package paradigwent.modelo.efectos;

import paradigwent.modelo.TipoLinea;

import java.util.EnumSet;
import java.util.Set;
import java.util.StringJoiner;

/** Ej: "Escarcha": la fuerza de las criaturas de ciertas líneas no pasa de 1. */
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

    @Override
    public String descripcion() {
        StringJoiner lineas = new StringJoiner(", ");
        for (TipoLinea linea : lineasAfectadas) {
            lineas.add(linea.getNombre());
        }
        return "La fuerza de cada criatura en " + lineas + " no puede superar "
                + fuerzaMaxima + ". Afecta a ambos jugadores.";
    }
}
