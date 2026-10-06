package paradigwent.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Guarda las cartas que ya fueron jugadas o eliminadas del tablero.
 */
public class PilaDescarte {

    private final List<Carta> cartas;

    public PilaDescarte() {
        this.cartas = new ArrayList<>();
    }

    public void agregar(Carta carta) {
        cartas.add(carta);
    }

    public List<Carta> getCartas() {
        return Collections.unmodifiableList(cartas);
    }
}
