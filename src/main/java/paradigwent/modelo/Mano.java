package paradigwent.modelo;

import java.util.List;

/**
 * Cartas disponibles que el jugador puede jugar durante la partida.
 */
public class Mano {

    private List<Carta> cartas;

    public Mano(List<Carta> cartasIniciales) {
        this.cartas = cartasIniciales;
    }

    public Carta elegirCarta(int indice) {
        // TODO: devolver la carta en esa posicion (validar que el indice exista)
        return null;
    }

    public void quitarCarta(Carta carta) {
        // TODO: sacar la carta de la mano una vez jugada
    }

    public boolean estaVacia() {
        return cartas.isEmpty();
    }

    public List<Carta> getCartas() {
        return cartas;
    }
}
