package paradigwent.modelo;

import paradigwent.modelo.exceptions.IndiceInvalidoException;

import java.util.List;

public class Mano {

    private List<Carta> cartas;

    public Mano(List<Carta> cartasIniciales) {
        this.cartas = cartasIniciales;
    }

    public Carta elegirCarta(int indice) {
        if (indice < 0 || indice >= cartas.size()) {
            throw new IndiceInvalidoException("No existe una carta en la posición " + indice);
        }
        return cartas.get(indice);
    }

    public void quitarCarta(Carta carta) {
        cartas.remove(carta);
    }

    public boolean estaVacia() {
        return cartas.isEmpty();
    }

    public List<Carta> getCartas() {
        return cartas;
    }
}