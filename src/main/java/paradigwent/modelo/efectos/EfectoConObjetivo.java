package paradigwent.modelo.efectos;

import paradigwent.modelo.Jugador;
import paradigwent.modelo.Tablero;
import paradigwent.modelo.objetivos.Objetivo;
import paradigwent.modelo.objetivos.SinObjetivo;

import java.util.List;

/**
 * Base de los efectos que actuan sobre un objetivo del tablero.
 * Las subclases solo dicen que candidatos hay y que hacen con el elegido.
 */
public abstract class EfectoConObjetivo implements Efecto {

    private final Bando bando;

    protected EfectoConObjetivo(Bando bando) {
        this.bando = bando;
    }

    @Override
    public List<Objetivo> objetivosPosibles(Jugador jugador, Tablero tablero) {
        List<Objetivo> candidatos = candidatos(bando.duenioPara(jugador, tablero), tablero);
        return candidatos.isEmpty() ? List.of(new SinObjetivo()) : candidatos;
    }

    protected abstract List<Objetivo> candidatos(Jugador duenio, Tablero tablero);

    protected Bando getBando() {
        return bando;
    }
}


