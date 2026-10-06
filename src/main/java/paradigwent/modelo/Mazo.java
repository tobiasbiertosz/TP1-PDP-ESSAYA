package paradigwent.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Cartas de un jugador que todavia no fueron repartidas a la mano.
 */
public class Mazo {

    private List<Carta> cartas;

    public Mazo(List<Carta> cartas) {
        this.cartas = new ArrayList<>(cartas);
    }

    public List<Carta> repartirMano(int cantidad) {
        Collections.shuffle(cartas);
        List<Carta> mano = new ArrayList<>();
        for (int i = 0; i < cantidad && !cartas.isEmpty(); i++) {
            mano.add(cartas.remove(0));
        }
        return mano;
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    public int cantidadDeCartas() {
        return cartas.size();
    }
}
