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

    /** Saca la ultima carta descartada, o null si el descarte esta vacio. */
    public Carta sacarUltima() {
        return cartas.isEmpty() ? null : cartas.remove(cartas.size() - 1);
    }
}
