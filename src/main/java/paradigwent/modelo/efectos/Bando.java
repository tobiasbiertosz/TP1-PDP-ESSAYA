package paradigwent.modelo.efectos;

import paradigwent.modelo.Jugador;
import paradigwent.modelo.Tablero;

/** De quien es el objetivo de un efecto, relativo a quien juega la carta. */
public enum Bando {
    PROPIO {
        @Override
        Jugador duenioPara(Jugador jugador, Tablero tablero) {
            return jugador;
        }
    },
    ENEMIGO {
        @Override
        Jugador duenioPara(Jugador jugador, Tablero tablero) {
            return tablero.oponenteDe(jugador);
        }
    };

    abstract Jugador duenioPara(Jugador jugador, Tablero tablero);
}
