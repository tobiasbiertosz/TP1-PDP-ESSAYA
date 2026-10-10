package paradigwent.modelo;

import paradigwent.modelo.objetivos.Objetivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Controla el desarrollo completo del juego, desde el inicio
 * hasta que alguno de los dos jugadores se queda sin vidas.
 */
public class Partida {

    public static final int CANTIDAD_CARTAS_MANO_INICIAL = 10;

    private final Jugador jugador1;
    private final Jugador jugador2;
    private final Random random;
    private final Historial historial;
    private final List<ObservadorDePartida> observadores;
    private Jugador primeroDeLaRonda;
    private Ronda rondaActual;
    private int rondasCerradas;

    public Partida(Jugador jugador1, Jugador jugador2, Random random) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.random = random;
        this.historial = new Historial();
        this.observadores = new ArrayList<>();
    }

    public void agregarObservador(ObservadorDePartida observador) {
        observadores.add(observador);
    }

    public void iniciar() {
        primeroDeLaRonda = random.nextBoolean() ? jugador1 : jugador2;
        iniciarNuevaRonda();
    }

    public void jugarCarta(Carta carta, Objetivo objetivo) {
        historial.registrar(describirJugada(carta, objetivo));
        rondaActual.jugarCarta(carta, objetivo);
        notificar(observador -> observador.cartaJugada(carta));
        cerrarRondaSiCorresponde();
    }

    public void pasar() {
        historial.registrar(rondaActual.getTurnoActual().getNombre() + " pasó.");
        rondaActual.pasar();
        notificar(ObservadorDePartida::jugadorPaso);
        cerrarRondaSiCorresponde();
    }

    public void rendirse(Jugador jugador) {
        historial.registrar(jugador.getNombre() + " se rindió.");
        jugador.rendirse();
    }

    private String describirJugada(Carta carta, Objetivo objetivo) {
        String jugada = rondaActual.getTurnoActual().getNombre() + " jugó " + carta.getNombre();
        String detalle = objetivo.descripcion();
        return detalle.isEmpty() ? jugada + "." : jugada + " sobre " + detalle + ".";
    }

    /**
     * Cierra la ronda si ambos pasaron. Es un bucle porque una ronda nueva puede
     * nacer ya terminada (ambos jugadores sin cartas pasan automaticamente).
     */
    private void cerrarRondaSiCorresponde() {
        while (!estaTerminada() && rondaActual.ambosPasaron()) {
            historial.registrar(resumirRonda());
            rondaActual.finalizarRonda();
            rondasCerradas++;
            notificar(ObservadorDePartida::rondaTerminada);
            if (!estaTerminada()) {
                primeroDeLaRonda = rondaActual.getTablero().oponenteDe(primeroDeLaRonda);
                iniciarNuevaRonda();
            }
        }
    }

    private void notificar(Consumer<ObservadorDePartida> aviso) {
        observadores.forEach(aviso);
    }

    /** Se arma antes de finalizar la ronda, porque finalizarla limpia el tablero. */
    private String resumirRonda() {
        Tablero tablero = rondaActual.getTablero();
        Jugador ganador = rondaActual.determinarGanador();
        String resultado = ganador == null
                ? "Empate: ambos pierden una vida."
                : "Gana " + ganador.getNombre() + "; "
                + tablero.oponenteDe(ganador).getNombre() + " pierde una vida.";
        return "Fin de la ronda " + (rondasCerradas + 1) + ": "
                + jugador1.getNombre() + " " + tablero.calcularFuerzaJugador(jugador1) + " - "
                + jugador2.getNombre() + " " + tablero.calcularFuerzaJugador(jugador2)
                + ". " + resultado;
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

    public Historial getHistorial() {
        return historial;
    }

    public int getRondasCerradas() {
        return rondasCerradas;
    }
}