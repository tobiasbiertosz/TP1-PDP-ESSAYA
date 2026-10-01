package paradigwent.modelo;

/**
 * Controla el desarrollo de una ronda: turnos alternados hasta que ambos pasen.
 */
public class Ronda {

    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private Jugador turnoActual;

    public Ronda(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tablero = new Tablero(jugador1, jugador2);
        this.turnoActual = jugador1;
    }

    public void jugarTurno(Carta cartaElegida) {
        if (cartaElegida != null) {
            turnoActual.jugarCarta(cartaElegida, tablero);
        } else {
            turnoActual.pasarTurno();
        }

        Jugador otroJugador = (turnoActual == jugador1) ? jugador2 : jugador1;
        if (!otroJugador.haPasado()) {
            turnoActual = otroJugador;
        }
        // si el otro ya pasó, turnoActual queda igual: el mismo jugador sigue jugando
    }

    public boolean ambosPasaron() {
        return jugador1.haPasado() && jugador2.haPasado();
    }

    /**
     * Devuelve el jugador con más fuerza. Si hay empate, devuelve null.
     */
    public Jugador determinarGanador() {
        int fuerza1 = tablero.calcularFuerzaJugador(jugador1);
        int fuerza2 = tablero.calcularFuerzaJugador(jugador2);

        if (fuerza1 > fuerza2) {
            return jugador1;
        } else if (fuerza2 > fuerza1) {
            return jugador2;
        } else {
            return null;
        }
    }

    public void finalizarRonda() {
        Jugador ganador = determinarGanador();

        if (ganador == null) {
            jugador1.perderVida();
            jugador2.perderVida();
        } else {
            Jugador perdedor = (ganador == jugador1) ? jugador2 : jugador1;
            perdedor.perderVida();
        }

        tablero.limpiarTablero();
    }

    public Jugador getTurnoActual() {
        return turnoActual;
    }

    public Tablero getTablero() {
        return tablero;
    }
}
