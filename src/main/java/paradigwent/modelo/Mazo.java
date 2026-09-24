package paradigwent.modelo;

import java.util.List;

/**
 * Cartas de un jugador que todavia no fueron repartidas a la mano.
 */
public class Mazo {

    private List<Carta> cartas;

    public Mazo(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public List<Carta> repartirMano(int cantidad) {
        // TODO: sortear "cantidad" cartas al azar, sacarlas del mazo y devolverlas
        return null;
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    public int cantidadDeCartas() {
        return cartas.size();
    }
}
