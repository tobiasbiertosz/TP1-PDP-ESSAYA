package paradigwent.modelo;

import paradigwent.modelo.objetivos.Objetivo;

/**
 * Controla el desarrollo de una ronda: turnos alternados hasta que ambos pasen.
 */
public class Ronda {

    private final Jugador jugador1;
    private final Jugador jugador2;
    private final Tablero tablero;
    private Jugador turnoActual;

    public Ronda(Jugador jugador1, Jugador jugador2, Jugador primero) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tablero = new Tablero(jugador1, jugador2);
        this.turnoActual = primero;
        pasarSiNoTieneCartas();
    }

    public void jugarCarta(Carta carta, Objetivo objetivo) {
        turnoActual.jugarCarta(carta, tablero, objetivo);
        avanzarTurno();
    }

    public void pasar() {
        turnoActual.pasarTurno();
        avanzarTurno();
    }

    private void avanzarTurno() {
        Jugador otroJugador = tablero.oponenteDe(turnoActual);
        if (!otroJugador.haPasado()) {
            turnoActual = otroJugador;
        }
        // si el otro ya paso, turnoActual queda igual: el mismo jugador sigue jugando
        pasarSiNoTieneCartas();
    }

    /** Sin cartas en la mano no hay nada para jugar: pasa automaticamente. */
    private void pasarSiNoTieneCartas() {
        if (!ambosPasaron() && turnoActual.sinCartas()) {
            pasar();
        }
    }

    public boolean ambosPasaron() {
        return jugador1.haPasado() && jugador2.haPasado();
    }

    /** Devuelve el jugador con mas fuerza. Si hay empate, devuelve null. */
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
            tablero.oponenteDe(ganador).perderVida();
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
