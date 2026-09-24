package paradigwent.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Guarda las cartas que ya fueron jugadas o eliminadas del tablero.
 */
public class PilaDescarte {

    private List<Carta> cartas;

    public PilaDescarte() {
        this.cartas = new ArrayList<>();
    }

    public void agregar(Carta carta) {
        cartas.add(carta);
    }

    public Carta recuperarUltima() {
        // TODO: usar esto para efectos de "resucitar carta del descarte"
        return null;
    }

    public List<Carta> getCartas() {
        return cartas;
    }
}
