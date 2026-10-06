package paradigwent.modelo;

import paradigwent.modelo.objetivos.Objetivo;

import java.util.Random;

/**
 * Controla el desarrollo completo del juego, desde el inicio
 * hasta que alguno de los dos jugadores se queda sin vidas.
 */
public class Partida {

    public static final int CANTIDAD_CARTAS_MANO_INICIAL = 10;

    private final Jugador jugador1;
    private final Jugador jugador2;
    private final Random random;
    private Jugador primeroDeLaRonda;
    private Ronda rondaActual;

    public Partida(Jugador jugador1, Jugador jugador2, Random random) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.random = random;
    }

    public void iniciar() {
        primeroDeLaRonda = random.nextBoolean() ? jugador1 : jugador2;
        iniciarNuevaRonda();
    }

    public void jugarCarta(Carta carta, Objetivo objetivo) {
        rondaActual.jugarCarta(carta, objetivo);
        cerrarRondaSiCorresponde();
    }

    public void pasar() {
        rondaActual.pasar();
        cerrarRondaSiCorresponde();
    }

    public void rendirse(Jugador jugador) {
        jugador.rendirse();
    }

    /**
     * Cierra la ronda si ambos pasaron. Es un bucle porque una ronda nueva puede
     * nacer ya terminada (ambos jugadores sin cartas pasan automaticamente).
     */
    private void cerrarRondaSiCorresponde() {
        while (!estaTerminada() && rondaActual.ambosPasaron()) {
            rondaActual.finalizarRonda();
            if (!estaTerminada()) {
                primeroDeLaRonda = rondaActual.getTablero().oponenteDe(primeroDeLaRonda);
                iniciarNuevaRonda();
            }
        }
    }

    private void iniciarNuevaRonda() {
        jugador1.reiniciarEstadoDeRonda();
        jugador2.reiniciarEstadoDeRonda();
        this.rondaActual = new Ronda(jugador1, jugador2, primeroDeLaRonda);
    }

    public boolean estaTerminada() {
        return jugador1.estaEliminado() || jugador2.estaEliminado();
    }

    /** Ganador de la partida, o null si todavia no termino (o ambos quedaron sin vidas). */
    public Jugador obtenerGanadorPartida() {
        if (jugador1.estaEliminado() && !jugador2.estaEliminado()) {
            return jugador2;
        } else if (jugador2.estaEliminado() && !jugador1.estaEliminado()) {
            return jugador1;
        }
        return null;
    }

    public Ronda getRondaActual() {
        return rondaActual;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }
}
