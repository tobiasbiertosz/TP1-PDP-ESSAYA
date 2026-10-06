package paradigwent.modelo;

import paradigwent.modelo.efectos.Efecto;
import paradigwent.modelo.efectos.EfectoClima;
import paradigwent.modelo.efectos.SinEfecto;
import paradigwent.modelo.objetivos.Objetivo;

import java.util.List;

public class Criatura extends Carta {

    private final int fuerzaBase;
    private final TipoLinea tipoLinea;
    private final Efecto habilidad;
    private int multiplicador;

    /** Criatura comun, sin habilidad. */
    public Criatura(String nombre, Faccion faccion, int fuerzaBase, TipoLinea tipoLinea) {
        this(nombre, faccion, fuerzaBase, tipoLinea, new SinEfecto());
    }

    public Criatura(String nombre, Faccion faccion, int fuerzaBase,
                    TipoLinea tipoLinea, Efecto habilidad) {
        super(nombre, faccion);
        this.fuerzaBase = fuerzaBase;
        this.tipoLinea = tipoLinea;
        this.habilidad = habilidad;
        this.multiplicador = 1;
    }

    @Override
    public List<Objetivo> objetivosPosibles(Jugador jugador, Tablero tablero) {
        return habilidad.objetivosPosibles(jugador, tablero);
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero, Objetivo objetivo) {
        tablero.colocarCriatura(jugador, this);
        habilidad.aplicar(jugador, tablero, objetivo);
    }

    /** El clima fija la fuerza base y despues se aplican las duplicaciones. */
    public int calcularFuerza(EfectoClima clima) {
        return clima.modificar(tipoLinea, fuerzaBase) * multiplicador;
    }

    public void duplicarFuerza() {
        multiplicador *= 2;
    }

    public void restablecerFuerza() {
        multiplicador = 1;
    }

    public int getFuerzaBase() {
        return fuerzaBase;
    }

    public TipoLinea getTipoLinea() {
        return tipoLinea;
    }
}
