package paradigwent.modelo;

import java.util.ArrayList;
import java.util.List;

public class Faccion {

    private String nombre;
    private List<Carta> cartasDisponibles;

    public Faccion(String nombre) {
        this.nombre = nombre;
        this.cartasDisponibles = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        cartasDisponibles.add(carta);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Carta> getCartasDisponibles() {
        return cartasDisponibles;
    }
}
