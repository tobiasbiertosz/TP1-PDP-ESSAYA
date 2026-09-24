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
        this.tablero = new Tablero();
        this.turnoActual = jugador1;
    }

    public void jugarTurno(Carta cartaElegida) {
        // TODO: si cartaElegida no es null, turnoActual.jugarCarta(cartaElegida, tablero)
        // TODO: si es null, turnoActual.pasarTurno()
        // TODO: pasarle el turno al otro jugador (si no paso)
    }

    public boolean ambosPasaron() {
        return jugador1.haPasado() && jugador2.haPasado();
    }

    public Jugador determinarGanador() {
        // TODO: comparar tablero.calcularFuerzaJugador(jugador1) vs jugador2
        // devolver el que tenga mas fuerza, o null si empatan
        return null;
    }

    public void finalizarRonda() {
        // TODO: segun el resultado de determinarGanador(), restar vidas
        // (si empatan, ambos pierden una vida)
        // TODO: llamar a tablero.limpiarTablero()
    }

    public Jugador getTurnoActual() {
        return turnoActual;
    }

    public Tablero getTablero() {
        return tablero;
    }
}
