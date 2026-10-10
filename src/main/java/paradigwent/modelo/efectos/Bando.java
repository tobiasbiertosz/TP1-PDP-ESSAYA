package paradigwent.modelo.efectos;

import paradigwent.modelo.Jugador;
import paradigwent.modelo.Tablero;

/** De quien es el objetivo de un efecto, relativo a quien juega la carta. */
public enum Bando {
    PROPIO("propia") {
        @Override
        Jugador duenioPara(Jugador jugador, Tablero tablero) {
            return jugador;
        }
    },
    ENEMIGO("enemiga") {
        @Override
        Jugador duenioPara(Jugador jugador, Tablero tablero) {
            return tablero.oponenteDe(jugador);
        }
    };

    private final String descripcion;

    Bando(String descripcion) {
        this.descripcion = descripcion;
    }

    /** Para armar frases: "una criatura propia", "una fila enemiga". */
    public String getDescripcion() {
        return descripcion;
    }

    abstract Jugador duenioPara(Jugador jugador, Tablero tablero);
}