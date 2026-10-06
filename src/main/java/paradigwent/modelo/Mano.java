package paradigwent.modelo;

import paradigwent.modelo.exceptions.CartaNoEstaEnManoException;
import paradigwent.modelo.exceptions.IndiceInvalidoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mano {

    private final List<Carta> cartas;

    public Mano(List<Carta> cartasIniciales) {
        this.cartas = new ArrayList<>(cartasIniciales);
    }

    public Carta elegirCarta(int indice) {
        if (indice < 0 || indice >= cartas.size()) {
            throw new IndiceInvalidoException("No existe una carta en la posición " + indice);
        }
        return cartas.get(indice);
    }

    public void quitarCarta(Carta carta) {
        if (!cartas.remove(carta)) {
            throw new CartaNoEstaEnManoException("La carta " + carta.getNombre() + " no está en la mano");
        }
    }

    public boolean estaVacia() {
        return cartas.isEmpty();
    }

    public List<Carta> getCartas() {
        return Collections.unmodifiableList(cartas);
    }
}
