package paradigwent.modelo;

/**
 * Version de Jugador que decide sus jugadas automaticamente (la computadora).
 */
public class JugadorIA extends Jugador {

    public JugadorIA(String nombre, Mazo mazo, Mano mano, Faccion faccion) {
        super(nombre, mazo, mano, faccion);
    }

    public Carta decidirJugada(Tablero tablero) {
        // TODO: implementar una logica simple, por ejemplo:
        // - si la mano esta vacia, no hay nada para jugar (pasar)
        // - si no, elegir alguna carta de la mano (al azar, o la de mayor fuerza)
        return null;
    }
}
