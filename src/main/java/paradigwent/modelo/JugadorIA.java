package paradigwent.modelo;

import paradigwent.modelo.objetivos.Objetivo;

import java.util.List;
import java.util.Random;

/**
 * Version de Jugador que decide sus jugadas automaticamente (la computadora).
 */
public class JugadorIA extends Jugador {

    private final Random random;

    public JugadorIA(String nombre, Mazo mazo, Mano mano, Faccion faccion, Random random) {
        super(nombre, mazo, mano, faccion);
        this.random = random;
    }

    /** Juega una carta de la mano o pasa. Solo se llama cuando es su turno. */
    public void jugarTurno(Partida partida) {
        Tablero tablero = partida.getRondaActual().getTablero();
        if (conviertePasar(tablero)) {
            partida.pasar();
            return;
        }
        Carta carta = getMano().elegirCarta(random.nextInt(getMano().getCartas().size()));
        List<Objetivo> objetivos = carta.objetivosPosibles(this, tablero);
        partida.jugarCarta(carta, objetivos.get(random.nextInt(objetivos.size())));
    }

    /** Si el rival ya paso y ya va ganando, no hace falta gastar mas cartas. */
    private boolean conviertePasar(Tablero tablero) {
        Jugador rival = tablero.oponenteDe(this);
        return rival.haPasado()
                && tablero.calcularFuerzaJugador(this) > tablero.calcularFuerzaJugador(rival);
    }
}
