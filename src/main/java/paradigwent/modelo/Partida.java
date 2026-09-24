package paradigwent.modelo;

/**
 * Controla el desarrollo completo del juego, desde el inicio
 * hasta que alguno de los dos jugadores se queda sin vidas.
 */
public class Partida {

    private Jugador jugador1;
    private Jugador jugador2;
    private Ronda rondaActual;
    private boolean terminada;

    public Partida(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.terminada = false;
    }

    public void iniciar() {
        iniciarNuevaRonda();
    }

    public void iniciarNuevaRonda() {
        jugador1.reiniciarEstadoDeRonda();
        jugador2.reiniciarEstadoDeRonda();
        this.rondaActual = new Ronda(jugador1, jugador2);
    }

    public boolean estaTerminada() {
        return jugador1.estaEliminado() || jugador2.estaEliminado();
    }

    public Jugador obtenerGanadorPartida() {
        // TODO: devolver el jugador que NO se quedo sin vidas
        return null;
    }

    public Ronda getRondaActual() {
        return rondaActual;
    }
}
